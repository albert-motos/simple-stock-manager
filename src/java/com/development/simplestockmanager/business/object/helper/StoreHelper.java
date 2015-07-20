package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.StoreJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Store object
 *
 * @author foxtrot
 */
public class StoreHelper extends CommonHelper {

    public StoreHelper() {
        super(BusinessConstant.QUERY.STORE);
    }

    public StoreJpaController getJpaController() {
        return new StoreJpaController(entityManagerFactory);
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Store.findAllByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Store.findEnableByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindForBrowserQuery(String city, String country, long employee, String name, String phone, String state, String street, long status,
            Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT s FROM Store s where 1 = 1"
                + (city.isEmpty() ? "" : " AND s.city LIKE '%" + city + "%'")
                + (country.isEmpty() ? "" : " AND s.country LIKE '%" + country + "%'")
                + (employee == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND c.employee.id = " + employee)
                + (name.isEmpty() ? "" : " AND s.name LIKE '%" + name + "%'")
                + (phone.isEmpty() ? "" : " AND s.phone LIKE '%" + phone + "%'")
                + (state.isEmpty() ? "" : " AND s.state LIKE '%" + state + "%'")
                + (street.isEmpty() ? "" : " AND s.street LIKE '%" + street + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND s.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND s.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
    
}
