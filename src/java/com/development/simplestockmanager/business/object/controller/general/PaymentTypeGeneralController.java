package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeNull;
import com.development.simplestockmanager.business.object.helper.PaymentTypeHelper;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.controller.PaymentTypeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeGeneralController {

    private final PaymentTypeJpaController controller;

    public PaymentTypeGeneralController() {
        PaymentTypeHelper helper = new PaymentTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(PaymentType paymentType) {
        try {
            controller.create(paymentType);
        } catch (Exception e) {
            paymentType = new PaymentTypeNull();
        }

        return paymentType.getId();
    }

    public PaymentType read(PaymentType paymentType) {
        try {
            paymentType = controller.findPaymentType(paymentType.getId());

            if (paymentType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            paymentType = new PaymentTypeNull();
        }

        return paymentType;
    }

    public long update(PaymentType paymentType) {
        long status;

        try {
            controller.edit(paymentType);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(PaymentType paymentType) {
        long status;

        try {
            controller.destroy(paymentType.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException | IllegalOrphanException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
