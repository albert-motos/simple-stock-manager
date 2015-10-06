package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Price object
 *
 * @author foxtrot
 */
public class PriceHelper extends CommonHelper {

    public PriceHelper() {
        super(BusinessConstant.QUERY.PRICE);
    }

    public PriceJpaController getJpaController() {
        return new PriceJpaController(entityManagerFactory);
    }
    
    public Query getFindForBrowserQuery(String title, long stockID, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, 
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT p FROM Price p where 1 = 1"
                + (title.isEmpty() ? "" : " AND p.title LIKE '%" + title + "%'")
                + (stockID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND p.stock.id = " + stockID)
                + (status == WebConstant.STATUS.HIDDEN ? " AND p.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND p.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
}
