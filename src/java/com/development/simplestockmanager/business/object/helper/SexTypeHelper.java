package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for SexType object
 * 
 * @author foxtrot
 */
public class SexTypeHelper extends CommonHelper implements BaseTypeHelper {

    public SexTypeHelper() {
        super(BusinessConstant.QUERY.SEX_TYPE);
    }

    public SexTypeJpaController getJpaController() {
        return new SexTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("SexType.findByType");
        query.setParameter("type", type);

        return query;
    }
    
    @Override
    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("SexType.findAll");

        return query;
    }

    @Override
    public Query getFindEnable() {
        Query query = entityManager.createNamedQuery("SexType.findByEnable");
        query.setParameter("enable", true);

        return query;
    }
    
    public Query getFindForBrowserQuery(String type, String translation, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT s FROM SexType e INNER JOIN s.sexTypeTranslationList AS st where 1 = 1"
                + (type.isEmpty() ? "" : " AND s.type LIKE '%" + type + "%'")
                + (translation.isEmpty() ? "" : " AND st.translation LIKE '%" + translation + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND s.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND s.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }
    
}
