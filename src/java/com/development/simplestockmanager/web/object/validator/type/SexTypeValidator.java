package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.web.object.validator.*;
import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.SexTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final SexTypeSpecificController specificController;
    private final SexTypeTranslationSpecificController translationController;
   
    private SexType sexType;
    private SexTypeTranslation translationES_ES;
    private SexTypeTranslation translationCA_ES;
    private SexTypeTranslation translationEN_US;
    

    public SexTypeValidator(long mode, LanguageController controller, SexTypeSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
        this.translationController = new SexTypeTranslationSpecificController();
    }
    
    @Override
    public void setObject(Object object) {
        sexType = (SexType) object;
    }
    
    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (SexTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (SexTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (SexTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (sexType.getType().isEmpty()) {
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

        if (!sexType.getType().isEmpty()) {
            SexType sexTypeOfType = specificController.findByType(sexType.getType());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && sexTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && sexTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(sexTypeOfType.getId(), sexType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }
        
        if (!translationEN_US.getTranslation().isEmpty()) {
            SexTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), sexType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.EN_US));
            }
        }
        
        if (!translationES_ES.getTranslation().isEmpty()) {
            SexTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), sexType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.ES_ES));
            }
        }
        
        if (!translationCA_ES.getTranslation().isEmpty()) {
            SexTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), sexType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }

        return causeList;
    }

}
