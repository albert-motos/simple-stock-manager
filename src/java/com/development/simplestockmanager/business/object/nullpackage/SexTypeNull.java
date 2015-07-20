package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.SexType;

/**
 * Null class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeNull extends SexType {

    public SexTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
