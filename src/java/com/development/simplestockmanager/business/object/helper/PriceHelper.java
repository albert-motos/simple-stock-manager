package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;

/**
 * Helper class for Price object
 *
 * @author foxtrot
 */
public class PriceHelper extends CommonHelper {

    public PriceHelper() {
        super(BusinessConstant.QUERY.PRICE);
    }

    public PriceJpaController getJpaController() {
        return new PriceJpaController(entityManagerFactory);
    }
}
