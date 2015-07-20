package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.EmployeeJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeHelper extends CommonHelper {

    public EmployeeHelper() {
        super(BusinessConstant.QUERY.EMPLOYEE);
    }

    public EmployeeJpaController getJpaController() {
        return new EmployeeJpaController(entityManagerFactory);
    }

    public Query getFindByCredentialsQuery(String username, String password) {
        Query query = entityManager.createNamedQuery("Employee.findByCredentials");
        query.setParameter("username", username);
        query.setParameter("password", password);

        return query;
    }

    public Query getFindBySessionQuery(String session) {
        Query query = entityManager.createNamedQuery("Employee.findBySession");
        query.setParameter("session", session);

        return query;
    }

    public Query getFindByUsernameQuery(String username) {
        Query query = entityManager.createNamedQuery("Employee.findByUsername");
        query.setParameter("username", username);

        return query;
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Employee.findAllByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Employee.findEnableByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindForBrowserQuery(Date bornDate, String email, long employeeTypeID, String firstname, long languageID, String lastname, String phone,
            long sexTypeID, String username, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo, 
            long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT e FROM Employee e where 1 = 1"
                + (bornDate ==  null ? "" : " AND e.bornDate = '" + new Timestamp(bornDate.getTime()) + "'")
                + (email.isEmpty() ? "" : " AND e.email LIKE '%" + email + "%'")
                + (employeeTypeID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND e.employeeType.id = " + employeeTypeID)
                + (firstname.isEmpty() ? "" : " AND e.firstname LIKE '%" + firstname + "%'")
                + (languageID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND e.language.id = " + employeeTypeID)
                + (lastname.isEmpty() ? "" : " AND e.lastname LIKE '%" + lastname + "%'")
                + (phone.isEmpty() ? "" : " AND e.phone LIKE '%" + phone + "%'")
                + (sexTypeID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND e.sexType.id = " + sexTypeID)
                + (username.isEmpty() ? "" : " AND e.username LIKE '%" + username + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND e.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND e.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }
}
