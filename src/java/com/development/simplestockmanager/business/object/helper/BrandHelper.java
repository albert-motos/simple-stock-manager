package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Brand object
 *
 * @author foxtrot
 */
public class BrandHelper extends CommonHelper {

    public BrandHelper() {
        super(BusinessConstant.QUERY.BRAND);
    }

    public BrandJpaController getJpaController() {
        return new BrandJpaController(entityManagerFactory);
    }

    public Query getFindByNameQuery(String name) {
        Query query = entityManager.createNamedQuery("Brand.findByName");
        query.setParameter("name", name);

        return query;
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Brand.findAllByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Brand.findEnableByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindForBrowserQuery(String name, String description, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT b FROM Brand b where 1 = 1"
                + (name.isEmpty() ? "" : " AND b.name LIKE '%" + name + "%'")
                + (description.isEmpty() ? "" : " AND b.description LIKE '%" + description + "%'")
                + (description.isEmpty() ? "" : " AND b.description LIKE '%" + description + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND b.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND b.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }
}
