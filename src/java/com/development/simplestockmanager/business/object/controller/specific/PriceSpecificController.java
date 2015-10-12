package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PriceHelper;
import com.development.simplestockmanager.business.persistence.Price;
import java.math.BigDecimal;
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
    
    public List<Price> findEnableByStock(String store, String product) {
        List<Price> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByStockQuery(store, product);
            for (Object object : query.getResultList()) {
                Price price = (Price) object;
                Date date = new Date();
                if (date.after(price.getInitialDate()) && date.before(price.getEndDate()) && price.getEndAmount().compareTo(BigDecimal.ZERO) == 1) {
                    list.add(price);
                }
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
