package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.PaymentTypeJpaController;

/**
 * Helper class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeHelper extends CommonHelper {

    public PaymentTypeJpaController getJpaController() {
        return new PaymentTypeJpaController(entityManagerFactory);
    }
}
