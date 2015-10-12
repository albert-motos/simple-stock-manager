package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.InvoiceJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceHelper extends CommonHelper {

    public InvoiceHelper() {
        super(BusinessConstant.QUERY.INVOICE);
    }

    public InvoiceJpaController getJpaController() {
        return new InvoiceJpaController(entityManagerFactory);
    }
    
    public Query getFindForBrowserQuery(long clientID, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT i FROM Invoice i where 1 = 1"
                + (clientID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND i.client.id = " + clientID)
                + (status == WebConstant.STATUS.HIDDEN ? " AND i.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND i.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
}
