package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.StockJpaController;

/**
 * Helper class for Stock object
 *
 * @author foxtrot
 */
public class StockHelper extends CommonHelper{

    public StockHelper() {
        super(BusinessConstant.QUERY.STOCK);
    }

    public StockJpaController getJpaController() {
        return new StockJpaController(entityManagerFactory);
    }
}
