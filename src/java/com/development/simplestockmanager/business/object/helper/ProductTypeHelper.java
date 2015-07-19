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
    
    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("ProductType.findByType");
        query.setParameter("type", type);

        return query;
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
