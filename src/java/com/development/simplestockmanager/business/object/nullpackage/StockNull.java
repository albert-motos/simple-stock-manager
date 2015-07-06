package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;

/**
 * Null class for Stock object
 *
 * @author foxtrot
 */
public class StockNull extends Stock {

    public StockNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
