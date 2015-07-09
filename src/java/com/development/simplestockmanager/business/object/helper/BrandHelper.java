package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Brand object
 *
 * @author foxtrot
 */
public class BrandHelper extends BaseHelper {

    public BrandJpaController getJpaController() {
        return new BrandJpaController(entityManagerFactory);
    }

    public Query getFindByNameQuery(String name) {
        Query query = entityManager.createNamedQuery("Brand.findByName");
        query.setParameter("name", name);

        return query;
    }
    
    public Query getFindByNameForSelectorQuery(String name) {
        Query query = entityManager.createNamedQuery("Brand.getFindByNameForSelector");
        query.setParameter("name", "%" + name + "%");

        return query;
    }
    
    public Query getFindForBrowserQuery(String name, String description, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT b FROM Brand b where 1 = 1"
                + (name.isEmpty() ? "" : " AND b.name LIKE '%" + name + "%'")
                + (description.isEmpty() ? "" : " AND b.description LIKE '%" + description + "%'")
                + getAuditoryQuery("b", createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);

        return entityManager.createNamedQuery(query);
    }
}
