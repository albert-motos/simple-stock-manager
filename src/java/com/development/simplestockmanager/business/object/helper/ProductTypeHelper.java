package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProductTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeHelper extends CommonHelper implements BaseTypeHelper {

    public ProductTypeJpaController getJpaController() {
        return new ProductTypeJpaController(entityManagerFactory);
    }

    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("ProductType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

    public Query getFindEnableForSelector(String language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("Product.findAll");

        return query;
    }

    @Override
    public Query getFindEnable() {
        Query query = entityManager.createNamedQuery("Product.findByEnable");
        query.setParameter("enable", true);

        return query;
    }

}
