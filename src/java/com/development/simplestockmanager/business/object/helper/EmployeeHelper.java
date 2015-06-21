/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeHelper {

    public static EmployeeJpaController getJpaController() {
        return new EmployeeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findById");
        query.setParameter("id", id);

        return query;
    }
    
    public static Query getFindByUserNameQuery(String userName) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findByUserName");
        query.setParameter("userName", userName);

        return query;
    }
    
    public static Query getFindByName(String name) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findByName");
        query.setParameter("name", name);
        
        return query;
    }
}
