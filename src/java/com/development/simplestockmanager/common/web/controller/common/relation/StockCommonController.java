package com.development.simplestockmanager.common.web.controller.common.relation;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.StockGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.StockSpecificController;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.object.validator.relation.StockValidator;

/**
 * Common controller class for Stock object
 *
 * @author foxtrot
 */
public class StockCommonController extends BaseCommonController {

    protected final StockValidator validator;
    protected final StockGeneralController generalController;
    protected final StockSpecificController specificController;

    protected Stock stock;
    protected StoreSelector storeSelector;
    protected ProductSelector productSelector;

    public StockCommonController(long mode) {
        generalController = new StockGeneralController();
        specificController = new StockSpecificController();
        validator = new StockValidator(mode, specificController);
    }

    public Stock getStock() {
        return stock;
    }

    public StoreSelector getStoreSelector() {
        return storeSelector;
    }

    public ProductSelector getProductSelector() {
        return productSelector;
    }

}
