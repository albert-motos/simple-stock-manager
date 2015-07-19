package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.EmployeeTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeSpecificController;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.web.object.validator.type.EmployeeTypeValidator;

/**
 * Common controller class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeCommonController extends BaseCommonController {

    protected final EmployeeTypeValidator validator;
    protected final EmployeeTypeGeneralController generalController;
    protected final EmployeeTypeSpecificController specificController;

    protected EmployeeType employeeType;

    public EmployeeTypeCommonController(long mode) {
        generalController = new EmployeeTypeGeneralController();
        specificController = new EmployeeTypeSpecificController();
//        validator = new EmployeeTypeValidator(mode, languageController, specificController);
        validator = new EmployeeTypeValidator();
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

}
