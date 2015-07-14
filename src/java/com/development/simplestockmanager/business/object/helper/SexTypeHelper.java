package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for SexType object
 * 
 * @author foxtrot
 */
public class SexTypeHelper extends CommonHelper implements BaseTypeHelper {

    public SexTypeJpaController getJpaController() {
        return new SexTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("SexType.findAll");

        return query;
    }

    @Override
    public Query getFindEnable() {
        Query query = entityManager.createNamedQuery("SexType.findByEnable");
        query.setParameter("enable", true);

        return query;
    }
    
}
