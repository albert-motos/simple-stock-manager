package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseTypeValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final PriceTypeSpecificController specificController;
    private final PriceTypeTranslationSpecificController translationController;
   
    private PriceType priceType;
    private PriceTypeTranslation translationES_ES;
    private PriceTypeTranslation translationCA_ES;
    private PriceTypeTranslation translationEN_US;
    

    public PriceTypeValidator(long mode, LanguageController controller, PriceTypeSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
        this.translationController = new PriceTypeTranslationSpecificController();
    }
    
    @Override
    public void setObject(Object object) {
        priceType = (PriceType) object;
    }
    
    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (PriceTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (PriceTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (PriceTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (priceType.getType().isEmpty()) {
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

        if (!priceType.getType().isEmpty()) {
            PriceType priceTypeOfType = specificController.findByType(priceType.getType());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && priceTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && priceTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(priceTypeOfType.getId(), priceType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }
        
        if (!translationEN_US.getTranslation().isEmpty()) {
            PriceTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), priceType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.EN_US));
            }
        }
        
        if (!translationES_ES.getTranslation().isEmpty()) {
            PriceTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), priceType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.ES_ES));
            }
        }
        
        if (!translationCA_ES.getTranslation().isEmpty()) {
            PriceTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), priceType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }

        return causeList;
    }

}
