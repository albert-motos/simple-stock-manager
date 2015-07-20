package com.development.simplestockmanager.web.controller.common.type;

import com.development.simplestockmanager.business.object.controller.general.EmployeeTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.EmployeeTypeTranslationGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.web.controller.common.BaseCommonController;
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
    protected final EmployeeTypeTranslationGeneralController translationGeneralController;
    protected final EmployeeTypeTranslationSpecificController translationSpecificController;

    protected EmployeeType employeeType;
    protected EmployeeTypeTranslation translationEN_US;
    protected EmployeeTypeTranslation translationES_ES;
    protected EmployeeTypeTranslation translationCA_ES;

    public EmployeeTypeCommonController(long mode) {
        generalController = new EmployeeTypeGeneralController();
        specificController = new EmployeeTypeSpecificController();
        translationGeneralController = new EmployeeTypeTranslationGeneralController();
        translationSpecificController = new EmployeeTypeTranslationSpecificController();
        validator = new EmployeeTypeValidator(mode, languageController, specificController);
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public EmployeeTypeTranslation getTranslationEN_US() {
        return translationEN_US;
    }

    public EmployeeTypeTranslation getTranslationES_ES() {
        return translationES_ES;
    }

    public EmployeeTypeTranslation getTranslationCA_ES() {
        return translationCA_ES;
    }

}
