package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeHelper;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Employee object
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

    public Employee findByUsername(String username) {
        Employee employee;

        try {
            Query query = helper.getFindByUsernameQuery(username);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee;
    }

    public List<Employee> fillSelectorByName(String name) {
        List<Employee> list = new ArrayList<>();

        try {
            Query query = helper.getFindByNameForSelectorQuery(name);
            for (Object object : query.getResultList()) {
                list.add((Employee) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Employee> fillSelector() {
        List<Employee> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((Employee) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

}
