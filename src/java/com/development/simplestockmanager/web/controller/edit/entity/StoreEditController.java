package com.development.simplestockmanager.web.controller.edit.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.StoreCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.web.object.selector.entity.EmployeeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Store object
 *
 * @author foxtrot
 */
@ManagedBean(name = "storeEdit")
@ViewScoped
public class StoreEditController extends StoreCommonController implements EditController {

    private Store baseStore;

    public StoreEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            store = (Store) receiveObjectFromSession(WebConstant.SESSION.STORE);
            baseStore = new Store(store);
        } catch (Exception e) {
            back();
        }
        
        employeeSelector = new EmployeeSelector(WebConstant.SELECTOR.MODE.ENABLE, store.getEmployee());
    }
    
    @Override
    public void edit() {
        if (store.equals(baseStore)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.ENTITY.STORE, store.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(store);

            if (validator.validate()) {
                store.setLastModifiedDate(new Date());
                store.setLastModifiedUser(user);

                Long status = generalController.update(store);

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.ENTITY.STORE, store.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }
    
    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.ENTITY.STORE);
    }
    
}
