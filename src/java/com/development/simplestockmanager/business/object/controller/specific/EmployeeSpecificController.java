package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeHelper;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeSpecificController {

    private final EmployeeHelper helper;

    public EmployeeSpecificController() {
        helper = new EmployeeHelper();
    }

    public Employee getEmployeeByCredencials(String username, String password) {
        Employee employee;
        
        try {
            Query query = helper.getFindByCredentialsQuery(username, password);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }
        
        return employee;
    }
    
    public Employee getEmployeeBySession(String session) {
        Employee employee;
        
        try {
            Query query = helper.getFindBySessionQuery(session);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }
        
        return employee;
    }

    public boolean usernameIsAvailable(String username) {
        Query query = helper.getFindByUsernameQuery(username);

        return query.getResultList().isEmpty();
    }

}
