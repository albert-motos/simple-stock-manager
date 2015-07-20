package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;

/**
 * Null class for Store object
 *
 * @author foxtrot
 */
public class StoreNull extends Store {

    public StoreNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
