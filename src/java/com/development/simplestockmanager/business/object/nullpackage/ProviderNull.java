package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Provider;

/**
 * Null class for Provider object
 *
 * @author foxtrot
 */
public class ProviderNull extends Provider {

    public ProviderNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
