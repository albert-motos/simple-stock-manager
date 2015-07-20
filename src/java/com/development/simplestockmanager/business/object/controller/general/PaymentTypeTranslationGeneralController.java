package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeTranslationNull;
import com.development.simplestockmanager.business.object.helper.PaymentTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.PaymentTypeTranslationJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for PaymentTypeTranslation object
 *
 * @author foxtrot
 */
public class PaymentTypeTranslationGeneralController {

    private final PaymentTypeTranslationJpaController controller;

    public PaymentTypeTranslationGeneralController() {
        PaymentTypeTranslationHelper helper = new PaymentTypeTranslationHelper();
        controller = helper.getJpaController();
    }

    public long create(PaymentTypeTranslation paymentTypeTranslation) {
        try {
            controller.create(paymentTypeTranslation);
        } catch (Exception e) {
            paymentTypeTranslation = new PaymentTypeTranslationNull();
        }

        return paymentTypeTranslation.getId();
    }

    public PaymentTypeTranslation read(PaymentTypeTranslation paymentTypeTranslation) {
        try {
            paymentTypeTranslation = controller.findPaymentTypeTranslation(paymentTypeTranslation.getId());

            if (paymentTypeTranslation == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            paymentTypeTranslation = new PaymentTypeTranslationNull();
        }

        return paymentTypeTranslation;
    }

    public long update(PaymentTypeTranslation paymentTypeTranslation) {
        long status;

        try {
            controller.edit(paymentTypeTranslation);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(PaymentTypeTranslation paymentTypeTranslation) {
        long status;

        try {
            controller.destroy(paymentTypeTranslation.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
