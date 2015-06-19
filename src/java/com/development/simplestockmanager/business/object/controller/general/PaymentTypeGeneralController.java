/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeNull;
import com.development.simplestockmanager.business.object.helper.PaymentTypeHelper;
import com.development.simplestockmanager.business.persistence.old.PaymentType;
import com.development.simplestockmanager.business.persistence.controller.old.PaymentTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class PaymentTypeGeneralController {

    public static long create(String type) {

        PaymentType paymentType;
        Query query = PaymentTypeHelper.getFindByTypeQuery(type);

        try {
            paymentType = (PaymentType) query.getSingleResult();
        } catch (Exception e) {
            paymentType = new PaymentType(type);

            try {
                PaymentTypeJpaController paymentTypeJpaController = PaymentTypeHelper.getJpaController();
                paymentTypeJpaController.create(paymentType);
            } catch (Exception e2) {
                paymentType = new PaymentTypeNull();
            }
        }

        return paymentType.getId();
    }

    public static PaymentType read(long id) {

        PaymentType paymentType;

        try {
            Query query = PaymentTypeHelper.getFindByIdQuery(id);
            paymentType = (PaymentType) query.getSingleResult();
        } catch (Exception e) {
            paymentType = new PaymentTypeNull();
        }

        return paymentType;
    }

    public static long update(long id, String type) {

        long state = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = PaymentTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                PaymentType paymentType = new PaymentType(id, type);

                try {
                    PaymentTypeJpaController paymentTypeJpaController = PaymentTypeHelper.getJpaController();
                    paymentTypeJpaController.edit(paymentType);
                    state = Constant.UPDATE.SUCCESS;
                } catch (Exception e) {
                }
            }
        }

        return state;
    }

    public static long delete(long id) {

        long state = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {

            try {
                PaymentTypeJpaController paymentTypeJpaController = PaymentTypeHelper.getJpaController();
                paymentTypeJpaController.destroy(id);
                state = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
