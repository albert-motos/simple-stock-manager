package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PriceHelper;
import com.development.simplestockmanager.business.object.nullpackage.PriceNull;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class PriceSpecificController {
    
    private final PriceHelper helper;

    public PriceSpecificController() {
        helper = new PriceHelper();
    }
    
    public List<Price> findAllForBrowser(Price browser, long stockID, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Price> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getTitle(), stockID, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, 
                    createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Price) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
