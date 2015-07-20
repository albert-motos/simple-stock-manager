package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseTypeValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final EmployeeTypeSpecificController specificController;
    private final EmployeeTypeTranslationSpecificController translationController;
   
    private EmployeeType employeeType;
    private EmployeeTypeTranslation translationES_ES;
    private EmployeeTypeTranslation translationCA_ES;
    private EmployeeTypeTranslation translationEN_US;
    

    public EmployeeTypeValidator(long mode, LanguageController controller, EmployeeTypeSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
        this.translationController = new EmployeeTypeTranslationSpecificController();
    }
    
    @Override
    public void setObject(Object object) {
        employeeType = (EmployeeType) object;
    }
    
    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (EmployeeTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (EmployeeTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (EmployeeTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (employeeType.getType().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.TYPE));
        }
        
        if (translationEN_US.getTranslation().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.TRANSLATION.EN_US));
        }
        
        if (translationES_ES.getTranslation().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.TRANSLATION.ES_ES));
        }
        
        if (translationCA_ES.getTranslation().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.TRANSLATION.CA_ES));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (!employeeType.getType().isEmpty()) {
            EmployeeType employeeTypeOfType = specificController.findByType(employeeType.getType());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && employeeTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && employeeTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(employeeTypeOfType.getId(), employeeType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }
        
        if (!translationEN_US.getTranslation().isEmpty()) {
            EmployeeTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), employeeType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.EN_US));
            }
        }
        
        if (!translationES_ES.getTranslation().isEmpty()) {
            EmployeeTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), employeeType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.ES_ES));
            }
        }
        
        if (!translationCA_ES.getTranslation().isEmpty()) {
            EmployeeTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), employeeType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }

        return causeList;
    }

}
