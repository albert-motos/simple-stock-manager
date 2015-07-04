package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class BrandHelper {

    public BrandJpaController getJpaController() {
        return new BrandJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public Query getFindByNameQuery(String name) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Brand.findByName");
        query.setParameter("name", name);
        
        return query;
    }
}
