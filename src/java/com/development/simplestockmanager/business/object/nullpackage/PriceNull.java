package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Price;

/**
 * Null class for Price object
 *
 * @author foxtrot
 */
public class PriceNull extends Price {

    public PriceNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
