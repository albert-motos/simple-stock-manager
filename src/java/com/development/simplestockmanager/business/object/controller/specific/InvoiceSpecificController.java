package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.InvoiceHelper;
import com.development.simplestockmanager.business.persistence.Invoice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Provider object
 *
 * @author foxtrot
 */
public class InvoiceSpecificController {

    private final InvoiceHelper helper;

    public InvoiceSpecificController() {
        helper = new InvoiceHelper();
    }

    public List<Invoice> findAllForBrowser(Invoice browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Invoice> list = new ArrayList<>();

        try {
             Query query = helper.getFindForBrowserQuery(browser.getClient().getId(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom,
                     lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Invoice) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    } 
}
