package com.development.simplestockmanager.web.controller.search.relation;

import com.development.simplestockmanager.business.object.nullpackage.RecordNull;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.relation.RecordCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Record object
 *
 * @author foxtrot
 */
@ManagedBean(name = "recordSearch")
@ViewScoped
public class RecordSearchController extends RecordCommonController implements SearchController {

    private Record browser;
    private List<Record> list;

    public RecordSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        
        browser = new Record();
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ALL);
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.ALL);
        
        clear();
    }

    @Override
    public void search() {
        Stock stock = new Stock();
        stock.setStore(storeSelector.getSelectedValue());
        stock.setProduct(productSelector.getSelectedValue());
        
        browser.setStock(stock);
        
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new RecordNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        storeSelector.clear();
        productSelector.clear();
        
        super.clear();
    }

    public void initView(Record record) {
        this.record = record;
    }
    
    public List<Record> getList() {
        return list;
    }

    public Record getBrowser() {
        return browser;
    }

    public void setBrowser(Record browser) {
        this.browser = browser;
    }

}
