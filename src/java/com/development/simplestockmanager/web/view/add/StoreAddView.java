package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.general.StoreGeneralController;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.component.selector.EmployeeSelector;
import com.development.simplestockmanager.web.object.validator.StoreValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Store object
 *
 * @author foxtrot
 */
@ManagedBean(name = "storeAdd")
@ViewScoped
public class StoreAddView extends BaseAddView {

    private final StoreValidator validator;
    private final StoreGeneralController generalController;

    private final Store store;
    private final EmployeeSelector employeeSelector;

    public StoreAddView() {
        validator = new StoreValidator(WebConstant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new StoreGeneralController();
        
        store = new Store();
        employeeSelector = new EmployeeSelector();
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();

        store.setEmployee(employeeSelector.getSelectedValue());
        validator.setObject(store);

        if (validator.validate()) {
            store.setCreatedDate(new Date());
            store.setLastModifiedDate(new Date());
            store.setCreatedUser(user);
            store.setLastModifiedUser(user);

            Long id = generalController.create(store);

            FacesMessage.Severity severity;
            String summary;
            String detail;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.STORE) + id +
                        internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));

        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public Store getStore() {
        return store;
    }

    public EmployeeSelector getEmployeeSelector() {
        return employeeSelector;
    }

}
