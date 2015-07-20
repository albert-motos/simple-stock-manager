package com.development.simplestockmanager.web.controller.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.StoreCommonController;
import com.development.simplestockmanager.web.object.selector.EmployeeSelector;
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
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.STORE) + id +
                        languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
