/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.InvoiceJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class InvoiceHelper {

    public static InvoiceJpaController getJpaController() {
        return new InvoiceJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Invoice.findById");
        query.setParameter("id", id);

        return query;
    }
}
