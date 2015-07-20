package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.ClientJpaController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Client object
 *
 * @author foxtrot
 */
public class ClientHelper extends CommonHelper {

    public ClientHelper() {
        super(BusinessConstant.QUERY.CLIENT);
    }

    public ClientJpaController getJpaController() {
        return new ClientJpaController(entityManagerFactory);
    }

    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Client.findAllByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Client.findEnableByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindForBrowserQuery(String firstname, String lastname, long sexType, Date bornDate, String phone, String email, long status,
            Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT c FROM Client c where 1 = 1"
                + (firstname.isEmpty() ? "" : " AND c.firstname LIKE '%" + firstname + "%'")
                + (lastname.isEmpty() ? "" : " AND c.lastname LIKE '%" + lastname + "%'")
                + (sexType == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND c.sexType.id = " + sexType)
                + (bornDate == null ? "" : " AND c.bornDate = '" + new Timestamp(bornDate.getTime()) + "'")
                + (phone.isEmpty() ? "" : " AND c.phone LIKE '%" + phone + "%'")
                + (email.isEmpty() ? "" : " AND c.email LIKE '%" + email + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND c.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND c.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
    
}
