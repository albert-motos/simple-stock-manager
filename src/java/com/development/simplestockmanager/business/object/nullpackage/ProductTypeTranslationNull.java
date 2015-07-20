package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;

/**
 * Null class for ProductTranslationType object
 *
 * @author foxtrot
 */
public class ProductTypeTranslationNull extends ProductTypeTranslation {

    public ProductTypeTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
