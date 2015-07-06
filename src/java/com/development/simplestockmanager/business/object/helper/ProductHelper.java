package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.controller.ProductJpaController;
import javax.persistence.Query;

/**
 * Helper class for Product object
 *
 * @author foxtrot
 */
public class ProductHelper {

    public ProductJpaController getJpaController() {
        return new ProductJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
    
    public Query getFindByRelationQuery(ProductType productType, Brand brand, Provider provider) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Product.findByRelation");
        query.setParameter("productType", productType);
        query.setParameter("brand", brand);
        query.setParameter("provider", provider);

        return query;
    }
}
