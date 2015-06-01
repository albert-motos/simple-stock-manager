/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.ProviderJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ProviderHelper {

    public static ProviderJpaController getJpaController() {
        return new ProviderJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Provider.findById");
        query.setParameter("id", id);

        return query;
    }
}