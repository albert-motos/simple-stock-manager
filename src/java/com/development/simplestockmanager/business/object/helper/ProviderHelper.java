package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Provider object
 *
 * @author foxtrot
 */
public class ProviderHelper extends CommonHelper {

    public ProviderHelper() {
        super(BusinessConstant.QUERY.PROVIDER);
    }

    public ProviderJpaController getJpaController() {
        return new ProviderJpaController(entityManagerFactory);
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Provider.findAllByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Provider.findEnableByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindForBrowserQuery(String email, String identifier, String name, String phone, long status, Date createdDateFrom,
            Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT p FROM Provider p where 1 = 1"
                + (email.isEmpty() ? "" : " AND p.email LIKE '%" + email + "%'")
                + (identifier.isEmpty() ? "" : " AND p.identifier LIKE '%" + identifier + "%'")
                + (name.isEmpty() ? "" : " AND p.name LIKE '%" + name + "%'")
                + (phone.isEmpty() ? "" : " AND p.phone LIKE '%" + phone + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND p.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND p.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
    
}
