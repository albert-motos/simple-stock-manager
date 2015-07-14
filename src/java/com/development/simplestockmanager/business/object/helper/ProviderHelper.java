package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import javax.persistence.Query;

/**
 * Helper class for Provider object
 *
 * @author foxtrot
 */
public class ProviderHelper extends CommonHelper {

    public ProviderJpaController getJpaController() {
        return new ProviderJpaController(entityManagerFactory);
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Provider.getFindAllByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Provider.getFindEnableByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
}
