package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.object.component.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.EmployeeValidator;

/**
 * Common controller class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeCommonController extends BaseCommonController {
    protected final EmployeeValidator validator;
    protected final EmployeeGeneralController generalController;
    protected final EmployeeSpecificController specificController;
    
    protected Employee employee;
    protected EmployeeTypeSelector employeeTypeSelector;
    protected SexTypeSelector sexTypeSelector;

    public EmployeeCommonController(long mode) {
        generalController = new EmployeeGeneralController();
        specificController = new EmployeeSpecificController();
        validator = new EmployeeValidator(mode, languageController, specificController);
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeTypeSelector getEmployeeTypeSelector() {
        return employeeTypeSelector;
    }

    public SexTypeSelector getSexTypeSelector() {
        return sexTypeSelector;
    }

}
