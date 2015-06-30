/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author foxtrot
 */
public class EmployeeSelectorView extends BaseSelector {

    private HashMap<String, Employee> hashMap;

    public EmployeeSelectorView() {
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

//        for (Employee employee : EmployeeSpecificController.findByName(browser)) {
//            String key = employee.getFirstname() + " " + employee.getLastname();
//            hashMap.put(key, employee);
//            list.add(key);
//        }
    }

    public Employee getSelectedValue() {
        Employee employee = new EmployeeNull();

        if (!selection.isEmpty()) {
//            employee = hashMa
        }

        return employee;
    }

}
