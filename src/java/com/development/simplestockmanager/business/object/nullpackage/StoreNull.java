package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Store;

/**
 * Null class for Store object
 *
 * @author foxtrot
 */
public class StoreNull extends Store {

    public StoreNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
