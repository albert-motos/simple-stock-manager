package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Item;

/**
 * Null class for Item object
 *
 * @author foxtrot
 */
public class ItemNull extends Item {

    public ItemNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
