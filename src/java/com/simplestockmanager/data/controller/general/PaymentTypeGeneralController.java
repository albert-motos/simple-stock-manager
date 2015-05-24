/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.PaymentTypeNull;
import com.simplestockmanager.helper.PaymentTypeHelper;
import com.simplestockmanager.persistence.PaymentType;
import com.simplestockmanager.persistence.controller.PaymentTypeJpaController;
import javax.persistence.Query;

/**
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

    public static PaymentType read(Long id) {
        PaymentType paymentType;

        try {
            Query query = PaymentTypeHelper.getFindByIdQuery(id);
            paymentType = (PaymentType) query.getSingleResult();
        } catch (Exception e) {
            paymentType = new PaymentTypeNull();
        }

        return paymentType;
    }

    public static long update(Long id, String type) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            PaymentType paymentType = new PaymentType(id, type);

            try {
                PaymentTypeJpaController paymentTypeJpaController = PaymentTypeHelper.getJpaController();
                paymentTypeJpaController.edit(paymentType);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(Long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                PaymentTypeJpaController paymentTypeJpaController = PaymentTypeHelper.getJpaController();
                paymentTypeJpaController.destroy(id);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
