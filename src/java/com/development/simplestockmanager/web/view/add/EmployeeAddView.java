package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.common.converter.EmployeeConverter;
import com.development.simplestockmanager.web.object.Employee;
import com.development.simplestockmanager.web.object.component.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.LanguageTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.EmployeeValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Employee object
 *
 * @author foxtrot
 */
@ViewScoped
@ManagedBean
public class EmployeeAddView extends BaseAddView {
    
    private final EmployeeValidator validator;
    private final EmployeeGeneralController controller;
    private final EmployeeConverter converter;

    private final EmployeeTypeSelector employeeTypeSelector;
    private final LanguageTypeSelector languageTypeSelector;
    private final SexTypeSelector sexTypeSelector;
    private final Employee employee;

    public EmployeeAddView() {
        validator = new EmployeeValidator();
        controller = new EmployeeGeneralController();
        converter = new EmployeeConverter();

        employee = new Employee();
        employeeTypeSelector = new EmployeeTypeSelector(user.getLanguageType().getCode());
        languageTypeSelector = new LanguageTypeSelector();
        sexTypeSelector = new SexTypeSelector(user.getLanguageType().getCode());
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue().getId());
        employee.setLanguageType(languageTypeSelector.getSelectedValue());
        employee.setSexType(sexTypeSelector.getSelectedValue().getId());
        validator.setObject(employee);

        if (validator.validate()) {
            com.development.simplestockmanager.business.persistence.Employee businessObject = converter.getBusinessObject(employee);
            businessObject.setCreatedDate(new Date());
            businessObject.setLastModifiedDate(new Date());
            businessObject.setCreatedUser(user.getUsername());
            businessObject.setLastModifiedUser(user.getUsername());
            businessObject.setLastOnlineDate(new Date(0));

            Long id = controller.create(businessObject);

            if (id == Constant.IDENTIFIER.INVALID) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Database server doesn't work properly"));
            } else {
                added = true;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Employee [" + id + "] is created properly"));
            }
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

    public Employee getEmployee() {
        return employee;
    }
    
}
