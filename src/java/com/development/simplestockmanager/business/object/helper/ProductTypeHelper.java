package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProductTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeHelper implements BaseTypeHelper {

    public ProductTypeJpaController getJpaController() {
        return new ProductTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("ProductType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
