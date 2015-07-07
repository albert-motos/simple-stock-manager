package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.InvoiceJpaController;

/**
 * Helper class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceHelper {

    public InvoiceJpaController getJpaController() {
        return new InvoiceJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
}
