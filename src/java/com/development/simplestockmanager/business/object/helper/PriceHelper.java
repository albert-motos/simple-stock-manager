/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class PriceHelper {

    public static PriceJpaController getJpaController() {
        return new PriceJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Price.findById");
        query.setParameter("id", id);

        return query;
    }
}
