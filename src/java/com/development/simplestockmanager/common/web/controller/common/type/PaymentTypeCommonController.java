package com.development.simplestockmanager.common.web.controller.common.type;

import com.development.simplestockmanager.business.object.controller.general.PaymentTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.PaymentTypeTranslationGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.web.object.validator.type.PaymentTypeValidator;

/**
 * Common controller class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeCommonController extends BaseCommonController {

    protected final PaymentTypeValidator validator;
    protected final PaymentTypeGeneralController generalController;
    protected final PaymentTypeSpecificController specificController;
    protected final PaymentTypeTranslationGeneralController translationGeneralController;
    protected final PaymentTypeTranslationSpecificController translationSpecificController;

    protected PaymentType paymentType;
    protected PaymentTypeTranslation translationEN_US;
    protected PaymentTypeTranslation translationES_ES;
    protected PaymentTypeTranslation translationCA_ES;

    public PaymentTypeCommonController(long mode) {
        generalController = new PaymentTypeGeneralController();
        specificController = new PaymentTypeSpecificController();
        translationGeneralController = new PaymentTypeTranslationGeneralController();
        translationSpecificController = new PaymentTypeTranslationSpecificController();
        validator = new PaymentTypeValidator(mode, languageController, specificController);
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentTypeTranslation getTranslationEN_US() {
        return translationEN_US;
    }

    public PaymentTypeTranslation getTranslationES_ES() {
        return translationES_ES;
    }

    public PaymentTypeTranslation getTranslationCA_ES() {
        return translationCA_ES;
    }

}
