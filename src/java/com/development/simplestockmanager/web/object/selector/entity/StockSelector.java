package com.development.simplestockmanager.web.object.selector.entity;

import com.development.simplestockmanager.common.web.object.selector.common.CommonSelector;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import com.development.simplestockmanager.business.object.controller.specific.StockSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.StockNull;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Selector class for Stock object
 *
 * @author foxtrot
 */
public class StockSelector implements BaseSelector {

    private final StockSpecificController specificController;
    private long mode;
    private String browser;
    private List<Stock> list;
    private Stock selection;

    public StockSelector(long mode) {
        this.specificController = new StockSpecificController();
        browser = "asd";
        search();
    }

    public StockSelector(long mode, Stock stock) {
        this(mode);
//        this.selection = getDisplayName(stock);
//        this.browser = this.selection;
        search();
    }

    @Override
    public void search() {
//        clear();
        list = new ArrayList<>();

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            list = specificController.findAllByBrowser(browser);
        } else {
            list = specificController.findEnableByBrowser(browser);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
//        hashMap = new HashMap<>();
    }

    public Stock getSelectedValue() {
        Stock stock = new StockNull();

        if (selection != null) {
            stock = selection;
        }

        return stock;
    }

    public String getDisplayName(Stock stock) {
        String name = "";

//        if (stock != null) {
//            name = stock.getName();
//        }

        return name;
    }

    private Object receiveObjectFromSession(String key) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) currentInstance.getExternalContext().getRequest();
        return request.getSession().getAttribute(key);
    }

    public String getBrowser() {
        return browser;
    }

    public List<Stock> getList() {
        return list;
    }

    public Stock getSelection() {
        return selection;
    }

    public void setSelection(Stock selection) {
        this.selection = selection;
    }

}
