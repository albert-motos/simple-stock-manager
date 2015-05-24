/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.PriceTypeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class PriceTypeHelper {

    public static PriceTypeJpaController getJpaController() {
        return new PriceTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("PriceType.findById");
        query.setParameter("id", id);

        return query;
    }
    
    public static Query getFindByTypeQuery(String type) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("PriceType.findByType");
        query.setParameter("type", type);
        
        return query;
    }
}
