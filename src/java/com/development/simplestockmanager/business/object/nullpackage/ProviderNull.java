package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Provider;

/**
 * Null class for Provider object
 *
 * @author foxtrot
 */
public class ProviderNull extends Provider {

    public ProviderNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
