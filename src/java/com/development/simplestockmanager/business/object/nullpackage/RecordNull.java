package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Record;

/**
 * Null class for Record object
 *
 * @author foxtrot
 */
public class RecordNull extends Record {

    public RecordNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
