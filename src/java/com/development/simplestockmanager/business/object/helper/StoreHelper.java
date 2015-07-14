package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.StoreJpaController;

/**
 * Helper class for Store object
 *
 * @author foxtrot
 */
public class StoreHelper extends CommonHelper {

    public StoreJpaController getJpaController() {
        return new StoreJpaController(entityManagerFactory);
    }
}
