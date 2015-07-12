package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import javax.persistence.Query;

/**
 * Helper class for Provider object
 *
 * @author foxtrot
 */
public class ProviderHelper extends BaseHelper {

    public ProviderJpaController getJpaController() {
        return new ProviderJpaController(entityManagerFactory);
    }
    public Query getFindByNameForSelectorQuery(String name) {
        Query query = entityManager.createNamedQuery("Provider.getFindByNameForSelector");
        query.setParameter("name","%" + name + "%");
        
        return query;
    }
    
    public Query getFindAll() {
        return entityManager.createNamedQuery("Provider.findAll");
    }
    
}
