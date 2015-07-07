package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductHelper;
import com.development.simplestockmanager.business.object.nullpackage.ProductNull;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.Provider;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ProductSpecificController {
    
    private final ProductHelper helper;

    public ProductSpecificController() {
        helper = new ProductHelper();
    }

    public Product findByRelation(ProductType productType, Brand brand, Provider provider) {
        Product product;
        
        try {
            Query query = helper.getFindByRelationQuery(productType, brand, provider);
            product = (Product) query.getSingleResult();
        } catch (Exception e) {
            product = new ProductNull();
        }
        
        return product;
    }
}
