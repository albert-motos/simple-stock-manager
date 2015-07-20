package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Product;

/**
 * Null class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class ProductNull extends Product {

    public ProductNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
