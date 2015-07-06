
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductHelper;
import com.development.simplestockmanager.business.persistence.Brand;
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

    public boolean relationIsAvailable(ProductType productType, Brand brand, Provider provider) {
        Query query = helper.getFindByRelationQuery(productType, brand, provider);

        return query.getResultList().isEmpty();
    }
}
