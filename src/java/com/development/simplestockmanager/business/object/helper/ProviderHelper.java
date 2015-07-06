package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import javax.persistence.Query;

/**
 * Helper class for Provider object
 *
 * @author foxtrot
 */
public class ProviderHelper {

    public ProviderJpaController getJpaController() {
        return new ProviderJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
    public Query getFindByNameForSelectorQuery(String name) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Provider.getFindByNameForSelector");
        query.setParameter("name","%" + name + "%");
        
        return query;
    }
    
}
