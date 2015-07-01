package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeJpaController;
import javax.persistence.Query;

/**
 * Helper class for Employee object
 * @author foxtrot
 */
public class EmployeeHelper {

    public EmployeeJpaController getJpaController() {
        return new EmployeeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
    
    public Query getFindByCredentialsQuery(String username, String password) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findByCredentials");
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        return query;
    }
    
    public Query getFindBySessionQuery(String session) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findBySession");
        query.setParameter("session", session);
        
        return query;
    }
    
    public Query getFindByUsernameQuery(String username) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findByUsername");
        query.setParameter("username", username);
        
        return query;
    }
}
