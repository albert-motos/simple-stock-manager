package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.ProductType;

/**
 * Null class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeNull extends ProductType {

    public ProductTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
