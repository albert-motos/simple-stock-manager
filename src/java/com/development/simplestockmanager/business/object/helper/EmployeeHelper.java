package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeJpaController;
import javax.persistence.Query;

/**
 * Helper class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeHelper extends CommonHelper {

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
        Query query = entityManager.createNamedQuery("Employee.getFindAllByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Employee.getFindEnableByBrowser");
        query.setParameter("browser","%" + browser + "%");
        
        return query;
    }
}
