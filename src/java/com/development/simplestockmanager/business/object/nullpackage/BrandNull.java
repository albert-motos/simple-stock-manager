package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Brand;

/**
 * Null class for Brand object
 *
 * @author foxtrot
 */
public class BrandNull extends Brand {

    public BrandNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
