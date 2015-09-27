package com.development.simplestockmanager.common.web.controller.common.relation;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.StockGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.RecordSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.StockSpecificController;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.object.validator.relation.StockValidator;

/**
 * Common controller class for Record object
 *
 * @author foxtrot
 */
public class RecordCommonController extends BaseCommonController {

    protected final RecordSpecificController specificController;

    protected Record record;
    protected StoreSelector storeSelector;
    protected ProductSelector productSelector;

    public RecordCommonController(long mode) {
        specificController = new RecordSpecificController();
    }

    public Record getRecord() {
        return record;
    }

    public StoreSelector getStoreSelector() {
        return storeSelector;
    }

    public ProductSelector getProductSelector() {
        return productSelector;
    }

}
