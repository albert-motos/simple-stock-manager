package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;

/**
 * Helper class for Provider object
 *
 * @author foxtrot
 */
public class ProviderHelper {

    public ProviderJpaController getJpaController() {
        return new ProviderJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
}
