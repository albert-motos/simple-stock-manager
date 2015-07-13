package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProductTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeHelper extends BaseHelper implements BaseTypeHelper {

    public ProductTypeJpaController getJpaController() {
        return new ProductTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("ProductType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

    @Override
    public Query getFindEnableForSelector(String language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
