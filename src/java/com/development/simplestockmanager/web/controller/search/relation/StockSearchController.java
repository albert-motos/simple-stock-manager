package com.development.simplestockmanager.web.controller.search.relation;

import com.development.simplestockmanager.business.object.nullpackage.StockNull;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.relation.StockCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Stock object
 *
 * @author foxtrot
 */
@ManagedBean(name = "stockSearch")
@ViewScoped
public class StockSearchController extends StockCommonController implements SearchController {

    private Stock browser;
    private List<Stock> list;

    public StockSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        
        browser = new Stock();
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ALL);
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.ALL);
        
        clear();
    }

    @Override
    public void search() {
        browser.setStore(storeSelector.getSelectedValue());
        browser.setProduct(productSelector.getSelectedValue());
        
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new StockNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        storeSelector.clear();
        productSelector.clear();
        
        super.clear();
    }

    public void initView(Stock stock) {
        this.stock = stock;
    }

    public void initEdit(Stock stock) {
        sendObjectToSession(WebConstant.SESSION.STOCK, stock);
        new NavigationService().redirect(WebConstant.WEB.EDIT.RELATION.STOCK);
    }
    
    public List<Stock> getList() {
        return list;
    }

    public Stock getBrowser() {
        return browser;
    }

    public void setBrowser(Stock browser) {
        this.browser = browser;
    }

}
