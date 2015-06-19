/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.persistence.old.EmployeeType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeTypeSpecificController {

    static public List<EmployeeType> getAll() {

        List<EmployeeType> list = new ArrayList<>();

        try {
            Query query = EmployeeTypeHelper.getAllQuery();

            for (Object object : query.getResultList()) {
                list.add((EmployeeType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
