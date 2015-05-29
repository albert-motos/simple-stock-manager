/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.specific;

import com.simplestockmanager.helper.EmployeeHelper;
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
}
