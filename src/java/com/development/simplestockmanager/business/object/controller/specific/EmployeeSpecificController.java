/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeHelper;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeSpecificController {

    static public boolean userNameIsAvailable(String userName) {
        Query query = EmployeeHelper.getFindByUserNameQuery(userName);

        return query.getResultList().isEmpty();
    }
    
    static public List<Employee> findByName(String name) {
        List<Employee> list = new ArrayList<>();
        Query query = EmployeeHelper.getFindByName(name);

        for(Object object : query.getResultList()) {
            list.add((Employee) object);
        }
        
        return list;
    }
}
