package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for SexTypeTranslation object
 * 
 * @author foxtrot
 */
public class SexTypeTranslationHelper extends CommonHelper implements BaseTypeHelper {

    public SexTypeJpaController getJpaController() {
        return new SexTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("SexTypeTranslation.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }
    
    @Override
    public Query getFindEnableForSelector(String language) {
        Query query = entityManager.createNamedQuery("SexTypeTranslation.findEnableForSelector");
        query.setParameter("language", language);

        return query;
    }
    
}
