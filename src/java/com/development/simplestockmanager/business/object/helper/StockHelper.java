package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.StockJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Stock object
 *
 * @author foxtrot
 */
public class StockHelper extends CommonHelper {

    public StockHelper() {
        super(BusinessConstant.QUERY.STOCK);
    }

    public StockJpaController getJpaController() {
        return new StockJpaController(entityManagerFactory);
    }
    
    public Query getFindForBrowserQuery(long productID, long storeID, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT s FROM Stock s where 1 = 1"
                + (productID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND s.product.id = " + productID)
                + (storeID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND s.store.id = " + storeID)
                + (status == WebConstant.STATUS.HIDDEN ? " AND s.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND s.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
    
    public Query getFindByRelationQuery(Product product, Store store) {
        Query query = entityManager.createNamedQuery("Stock.findByRelation");
        query.setParameter("product", product);
        query.setParameter("store", store);

        return query;
    }
}
