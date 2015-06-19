/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.old.StockJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class StockHelper {

    public static StockJpaController getJpaController() {
        return new StockJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Stock.findById");
        query.setParameter("id", id);

        return query;
    }
}
