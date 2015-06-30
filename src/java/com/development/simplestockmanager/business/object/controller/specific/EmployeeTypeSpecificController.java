/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeTypeSpecificController {

    private final String language;

    public EmployeeTypeSpecificController(String language) {
        this.language = language;
    }

    public List<EmployeeType> fillSelector() {
        List<EmployeeType> list = new ArrayList<>();

        try {
            Query query = new EmployeeTypeHelper().getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((EmployeeType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
