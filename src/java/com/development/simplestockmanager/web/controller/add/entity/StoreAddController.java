package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.StoreCommonController;
import com.development.simplestockmanager.web.object.selector.entity.EmployeeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Store object
 *
 * @author foxtrot
 */
@ManagedBean(name = "storeAdd")
@ViewScoped
public class StoreAddController extends StoreCommonController implements AddController {

    public StoreAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        store = new Store();
        employeeSelector = new EmployeeSelector(WebConstant.SELECTOR.MODE.ENABLE);
    }

    @Override
    public void add() {
        store.setEmployee(employeeSelector.getSelectedValue());
        validator.setObject(store);
        
        if (validator.validate()) {
            store.setCreatedDate(new Date());
            store.setLastModifiedDate(new Date());
            store.setCreatedUser(user);
            store.setLastModifiedUser(user);

            Long id = generalController.create(store);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.ENTITY.STORE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
