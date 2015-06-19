package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Product;

/**
 * Null class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class ProductNull extends Product {

    public ProductNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
