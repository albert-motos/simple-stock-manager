/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.old.BrandJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class BrandHelper {

    public static BrandJpaController getJpaController() {
        return new BrandJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByNameQuery(String name) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Brand.findByName");
        query.setParameter("name", name);
        
        return query;
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Brand.findById");
        query.setParameter("id", id);

        return query;
    }
}
