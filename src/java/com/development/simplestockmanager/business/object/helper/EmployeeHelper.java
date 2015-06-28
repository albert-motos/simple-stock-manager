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
    
    public static Query getFindByUsernameQuery(String username) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findByUsername");
        query.setParameter("username", username);
        
        return query;
    }
}
