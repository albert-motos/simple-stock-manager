package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.StockJpaController;

/**
 * Helper class for Stock object
 *
 * @author foxtrot
 */
public class StockHelper extends BaseHelper{

    public StockJpaController getJpaController() {
        return new StockJpaController(entityManagerFactory);
    }
}
