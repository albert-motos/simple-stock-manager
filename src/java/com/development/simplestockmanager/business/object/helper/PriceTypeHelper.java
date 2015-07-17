package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.PriceTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeHelper extends CommonHelper implements BaseTypeHelper {

    public PriceTypeJpaController getJpaController() {
        return new PriceTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("PriceType.findByType");
        query.setParameter("type", type);

        return query;
    }

    @Override
    public Query getFindAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Query getFindEnable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
