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

        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, employee.getSexType());
        employeeTypeSelector = new EmployeeTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, employee.getEmployeeType());
        languageSelector = new LanguageSelector(WebConstant.SELECTOR.MODE.NONE, employee.getLanguage());
    }

    @Override
    public void edit() {
        employee.setSexType(sexTypeSelector.getSelectedValue());
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue());
        employee.setLanguage(languageSelector.getSelectedValue());

        if (employee.equals(baseEmployee)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.ENTITY.EMPLOYEE, employee.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(employee);

            if (validator.validate()) {
                employee.setLastModifiedDate(new Date());
                employee.setLastModifiedUser(user);

                Long feedback = generalController.update(employee);

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.ENTITY.EMPLOYEE, employee.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
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
