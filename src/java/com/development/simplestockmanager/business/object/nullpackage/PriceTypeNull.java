package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.PriceType;

/**
 * Null class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeNull extends PriceType {

    public PriceTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
