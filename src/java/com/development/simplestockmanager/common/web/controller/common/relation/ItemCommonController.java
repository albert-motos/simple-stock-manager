package com.development.simplestockmanager.common.web.controller.common.relation;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.StockGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.StockSpecificController;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.object.validator.relation.StockValidator;
import java.util.List;

/**
 * Common controller class for Stock object
 *
 * @author foxtrot
 */
public class ItemCommonController extends BaseCommonController {

    protected final StockValidator validator;
    protected final StockGeneralController generalController;
    protected final StockSpecificController specificController;

    protected List<Item> list;
    protected StoreSelector storeSelector;
    protected ProductSelector productSelector;

    public ItemCommonController(long mode) {
        generalController = new StockGeneralController();
        specificController = new StockSpecificController();
        validator = new StockValidator(mode, specificController);
    }

    public List<Item> getList() {
        return list;
    }

    public StoreSelector getStoreSelector() {
        return storeSelector;
    }

    public ProductSelector getProductSelector() {
        return productSelector;
    }

    public void setStoreSelector(StoreSelector storeSelector) {
        this.storeSelector = storeSelector;
    }

}
