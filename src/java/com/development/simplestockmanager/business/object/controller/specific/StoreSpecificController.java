package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.StoreHelper;
import com.development.simplestockmanager.business.persistence.Store;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Store object
 *
 * @author foxtrot
 */
public class StoreSpecificController {

    private final StoreHelper helper;

    public StoreSpecificController() {
        helper = new StoreHelper();
    }

    public List<Store> findAllForBrowser(Store browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Store> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getCity(), browser.getCountry(), browser.getEmployee().getId(), browser.getName(),
                    browser.getPhone(), browser.getState(), browser.getStreet(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom,
                    lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Store) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<Store> getFindAllByBrowser(String browser) {
        List<Store> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Store) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Store> getFindEnableByBrowser(String browser) {
        List<Store> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Store) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
