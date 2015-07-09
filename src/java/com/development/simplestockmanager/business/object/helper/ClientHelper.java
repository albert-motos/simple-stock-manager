package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ClientJpaController;

/**
 * Helper class for Client object
 *
 * @author foxtrot
 */
public class ClientHelper extends BaseHelper {

    public ClientJpaController getJpaController() {
        return new ClientJpaController(entityManagerFactory);
    }
}
