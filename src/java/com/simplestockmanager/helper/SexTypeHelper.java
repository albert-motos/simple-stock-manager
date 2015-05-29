/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.SexTypeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class SexTypeHelper {

    public static SexTypeJpaController getJpaController() {
        return new SexTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.findById");
        query.setParameter("id", id);

        return query;
    }

    public static Query getFindByTypeQuery(String type) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.findByType");
        query.setParameter("type", type);

        return query;
    }

    public static Query getAllQuery() {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.findAll");

        return query;
    }
}
