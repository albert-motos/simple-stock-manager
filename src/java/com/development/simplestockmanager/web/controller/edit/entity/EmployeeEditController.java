package com.development.simplestockmanager.web.controller.edit.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.common.web.controller.common.entity.EmployeeCommonController;
import com.development.simplestockmanager.web.object.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.selector.type.LanguageSelector;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Employee object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeEdit")
@ViewScoped
public class EmployeeEditController extends EmployeeCommonController implements EditController {

    private Employee baseEmployee;

    public EmployeeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            employee = (Employee) receiveObjectFromSession(WebConstant.SESSION.EMPLOYEE);
            baseEmployee = new Employee(employee);
        } catch (Exception e) {
            back();
        }

        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage(), employee.getSexType());
        employeeTypeSelector = new EmployeeTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage(), employee.getEmployeeType());
        languageSelector = new LanguageSelector(WebConstant.SELECTOR.MODE.NONE, languageController.getLanguage(), employee.getLanguage());
    }

    @Override
    public void edit() {
        employee.setSexType(sexTypeSelector.getSelectedValue());
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue());
        employee.setLanguage(languageSelector.getSelectedValue());

        if (employee.equals(baseEmployee)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.EMPLOYEE) + employee.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(employee);

            if (validator.validate()) {
                employee.setLastModifiedDate(new Date());
                employee.setLastModifiedUser(user);

                Long status = generalController.update(employee);

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.EMPLOYEE) + employee.getId()
                            + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.EDIT);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.ENTITY.EMPLOYEE);
    }

}
