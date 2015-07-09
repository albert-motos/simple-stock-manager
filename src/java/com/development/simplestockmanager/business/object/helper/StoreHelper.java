package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.StoreJpaController;

/**
 * Helper class for Store object
 *
 * @author foxtrot
 */
public class StoreHelper extends BaseHelper {

    public StoreJpaController getJpaController() {
        return new StoreJpaController(entityManagerFactory);
    }
}
