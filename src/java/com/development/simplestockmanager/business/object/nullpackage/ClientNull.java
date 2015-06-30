package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Client;

/**
 * Null class for Client object
 *
 * @author foxtrot
 */
public class ClientNull extends Client {

    public ClientNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
