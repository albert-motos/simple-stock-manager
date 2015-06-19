/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.old.StoreJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class StoreHelper {

    public static StoreJpaController getJpaController() {
        return new StoreJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Store.findById");
        query.setParameter("id", id);

        return query;
    }
}
