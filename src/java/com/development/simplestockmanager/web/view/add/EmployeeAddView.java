package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.component.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.LanguageTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.EmployeeValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Employee object
 *
 * @author foxtrot
 */
@ViewScoped
@ManagedBean(name = "employeeAdd")
public class EmployeeAddView extends BaseAddView {
    
    private final EmployeeValidator validator;
    private final EmployeeGeneralController generalController;

    private final EmployeeTypeSelector employeeTypeSelector;
    private final LanguageTypeSelector languageTypeSelector;
    private final SexTypeSelector sexTypeSelector;
    private final Employee employee;

    public EmployeeAddView() {
        validator = new EmployeeValidator(Constant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new EmployeeGeneralController();

        employee = new Employee();
        employeeTypeSelector = new EmployeeTypeSelector(internationalizationController.getLanguage());
        languageTypeSelector = new LanguageTypeSelector(internationalizationController.getLanguage());
        sexTypeSelector = new SexTypeSelector(internationalizationController.getLanguage());
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue());
        employee.setLanguageType(languageTypeSelector.getSelectedValue());
        employee.setSexType(sexTypeSelector.getSelectedValue());
        validator.setObject(employee);

        if (validator.validate()) {
            employee.setCreatedDate(new Date());
            employee.setLastModifiedDate(new Date());
            employee.setCreatedUser(user.getUsername());
            employee.setLastModifiedUser(user.getUsername());
            employee.setLastOnlineDate(new Date(0));

            Long id = generalController.create(employee);

            Severity severity;
            String summary;
            String detail;

            if (id == Constant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.OBJECT.EMPLOYEE) + id +
                        internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public SexTypeSelector getSexTypeSelector() {
        return sexTypeSelector;
    }

    public EmployeeTypeSelector getEmployeeTypeSelector() {
        return employeeTypeSelector;
    }

    public LanguageTypeSelector getLanguageTypeSelector() {
        return languageTypeSelector;
    }

    public Employee getEmployee() {
        return employee;
    }
    
}
