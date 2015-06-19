/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.old.DayTypeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class DayTypeHelper {

    public static DayTypeJpaController getJpaController() {
        return new DayTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("DayType.findById");
        query.setParameter("id", id);

        return query;
    }
    
    public static Query getFindByTypeQuery(String type) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("DayType.findByType");
        query.setParameter("type", type);
        
        return query;
    }
}
