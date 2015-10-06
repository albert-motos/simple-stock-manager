package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.StockHelper;
import com.development.simplestockmanager.business.object.nullpackage.StockNull;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.Store;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Stock object
 *
 * @author foxtrot
 */
public class StockSpecificController {

    private final StockHelper helper;

    public StockSpecificController() {
        helper = new StockHelper();
    }
    
    public Stock findByRelation(Product product, Store store) {
        Stock stock;
        
        try {
            Query query = helper.getFindByRelationQuery(product, store);
            stock = (Stock) query.getSingleResult();
        } catch (Exception e) {
            stock = new StockNull();
        }
        
        return stock;
    }

    public List<Stock> findAllForBrowser(Stock browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Stock> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getProduct().getId(), browser.getStore().getId(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom,
                    lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Stock) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Stock> findAllByBrowser(String browser) {
        List<Stock> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllByBrowserQuery(browser);
            for (Object object : query.getResultList()) {
                list.add((Stock) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Stock> findEnableByBrowser(String browser) {
        List<Stock> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByBrowserQuery( browser);
            for (Object object : query.getResultList()) {
                list.add((Stock) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
