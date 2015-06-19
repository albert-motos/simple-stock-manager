package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.old.Stock;

/**
 * Null class for Stock object
 *
 * @author foxtrot
 */
public class StockNull extends Stock {

    public StockNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
