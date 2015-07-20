package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseTypeValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final PaymentTypeSpecificController specificController;
    private final PaymentTypeTranslationSpecificController translationController;
   
    private PaymentType paymentType;
    private PaymentTypeTranslation translationES_ES;
    private PaymentTypeTranslation translationCA_ES;
    private PaymentTypeTranslation translationEN_US;
    

    public PaymentTypeValidator(long mode, LanguageController controller, PaymentTypeSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
        this.translationController = new PaymentTypeTranslationSpecificController();
    }
    
    @Override
    public void setObject(Object object) {
        paymentType = (PaymentType) object;
    }
    
    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (PaymentTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (PaymentTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (PaymentTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (paymentType.getType().isEmpty()) {
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

        if (!paymentType.getType().isEmpty()) {
            PaymentType paymentTypeOfType = specificController.findByType(paymentType.getType());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && paymentTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && paymentTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(paymentTypeOfType.getId(), paymentType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }
        
        if (!translationEN_US.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.EN_US));
            }
        }
        
        if (!translationES_ES.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TRANSLATION.ES_ES));
            }
        }
        
        if (!translationCA_ES.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.TYPE));
            }
        }

        return causeList;
    }

}
