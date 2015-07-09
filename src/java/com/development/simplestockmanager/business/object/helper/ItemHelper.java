package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ItemJpaController;

/**
 * Helper class for Item object
 *
 * @author foxtrot
 */
public class ItemHelper extends BaseHelper {

    public ItemJpaController getJpaController() {
        return new ItemJpaController(entityManagerFactory);
    }
}
