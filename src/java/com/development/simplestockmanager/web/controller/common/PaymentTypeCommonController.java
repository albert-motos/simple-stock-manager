package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.PaymentTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeSpecificController;
import com.development.simplestockmanager.business.persistence.PaymentType;
//import com.development.simplestockmanager.web.object.validator.PaymentTypeValidator;

/**
 * Common controller class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeCommonController extends BaseCommonController {

//    protected final PaymentTypeValidator validator;
    protected final PaymentTypeGeneralController generalController;
    protected final PaymentTypeSpecificController specificController;

    protected PaymentType paymentType;

    public PaymentTypeCommonController(long mode) {
        generalController = new PaymentTypeGeneralController();
        specificController = new PaymentTypeSpecificController();
//        validator = new PaymentTypeValidator(mode, languageController, specificController);
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

}
