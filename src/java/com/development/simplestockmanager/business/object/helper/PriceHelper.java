package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;

/**
 * Helper class for Price object
 *
 * @author foxtrot
 */
public class PriceHelper extends CommonHelper {

    public PriceJpaController getJpaController() {
        return new PriceJpaController(entityManagerFactory);
    }
}
