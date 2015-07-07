package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for SexType object
 * 
 * @author foxtrot
 */
public class SexTypeHelper implements BaseTypeHelper {

    public SexTypeJpaController getJpaController() {
        return new SexTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }
    
    public Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.findById");
        query.setParameter("id", id);

        return query;
    }

    public Query getFindByRefencedType(String type) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.getFindByRefencedType");
        query.setParameter("type", type);

        return query;
    }

    public Query getFindByRefencedTypeAndLanguage(String type, String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("SexType.getFindByRefencedTypeAndLanguage");
        query.setParameter("type", type);
        query.setParameter("language", language);

        return query;
    }
}
