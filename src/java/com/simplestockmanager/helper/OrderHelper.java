/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.OrderJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class OrderHelper {

    public static OrderJpaController getJpaController() {
        return new OrderJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Order.findById");
        query.setParameter("id", id);

        return query;
    }
}
