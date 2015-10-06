package com.development.simplestockmanager.common.web.controller.common.entity;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.PriceGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.PriceSpecificController;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StockSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.object.validator.entity.PriceValidator;

/**
 * Common controller class for Price object
 *
 * @author foxtrot
 */
public class PriceCommonController extends BaseCommonController {

    protected final PriceValidator validator;
    protected final PriceGeneralController generalController;
    protected final PriceSpecificController specificController;

    protected Price price;
    protected StockSelector stockSelector;

    public PriceCommonController(long mode) {
        generalController = new PriceGeneralController();
        specificController = new PriceSpecificController();
        validator = new PriceValidator(mode, specificController);
    }

    public Price getPrice() {
        return price;
    }

    public StockSelector getStockSelector() {
        return stockSelector;
    }

}
