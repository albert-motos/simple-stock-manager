package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PaymentTypeJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeHelper extends CommonHelper implements BaseTypeHelper {

    public PaymentTypeHelper() {
        super(BusinessConstant.QUERY.PAYMENT_TYPE);
    }

    public PaymentTypeJpaController getJpaController() {
        return new PaymentTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("PaymnetType.findByType");
        query.setParameter("type", type);

        return query;
    }

    @Override
    public Query getFindAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Query getFindEnable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Query getFindForBrowserQuery(String type, String translation, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT p FROM PaymentType p INNER JOIN p.paymentTypeTranslationList AS pt where 1 = 1"
                + (type.isEmpty() ? "" : " AND e.type LIKE '%" + type + "%'")
                + (translation.isEmpty() ? "" : " AND pt.translation LIKE '%" + translation + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND p.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND p.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }
    
}
