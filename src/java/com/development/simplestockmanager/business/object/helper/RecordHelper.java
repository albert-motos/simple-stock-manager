package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.RecordJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Record object
 *
 * @author foxtrot
 */
public class RecordHelper extends CommonHelper {

    public RecordHelper() {
        super(BusinessConstant.QUERY.RECORD);
    }

    public RecordJpaController getJpaController() {
        return new RecordJpaController(entityManagerFactory);
    }
    
    public Query getFindForBrowserQuery(long productID, long storeID, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT r FROM Record r inner join Stock s on r.stock.id = s.id where 1 = 1"
                + (productID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND s.product.id = " + productID)
                + (storeID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND s.store.id = " + storeID)
                + (status == WebConstant.STATUS.HIDDEN ? " AND r.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND r.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
}
