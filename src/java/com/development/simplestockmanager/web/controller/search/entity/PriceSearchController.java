package com.development.simplestockmanager.web.controller.search.entity;

import com.development.simplestockmanager.business.object.nullpackage.PriceNull;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.PriceCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.entity.StockSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Price object
 *
 * @author foxtrot
 */
@ManagedBean(name = "priceSearch")
@ViewScoped
public class PriceSearchController extends PriceCommonController implements SearchController {

    private Price browser;
    private List<Price> list;

    public PriceSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        clear();
    }

    @Override
    public void search() {
        list = specificController.findAllForBrowser(browser, stockSelector.getSelectedValue().getId(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new PriceNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        stockSelector = new StockSelector(WebConstant.SELECTOR.MODE.ALL);
        
        super.clear();
    }

    public void initView(Price price) {
        this.price = price;
    }

    public void initEdit(Price price) {
        sendObjectToSession(WebConstant.SESSION.PRICE, price);
        new NavigationService().redirect(WebConstant.WEB.EDIT.ENTITY.PRICE);
    }
    
    public List<Price> getList() {
        return list;
    }

    public Price getBrowser() {
        return browser;
    }

    public void setBrowser(Price browser) {
        this.browser = browser;
    }

}
