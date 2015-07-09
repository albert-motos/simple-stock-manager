package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.RecordJpaController;

/**
 * Helper class for Record object
 *
 * @author foxtrot
 */
public class RecordHelper extends BaseHelper {

    public RecordJpaController getJpaController() {
        return new RecordJpaController(entityManagerFactory);
    }
}
