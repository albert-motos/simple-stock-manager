package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PriceType;

/**
 * Null class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeNull extends PriceType {

    public PriceTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
