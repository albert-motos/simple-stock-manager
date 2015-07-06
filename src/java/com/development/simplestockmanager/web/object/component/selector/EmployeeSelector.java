package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeSelector extends BaseSelector {

    private HashMap<String, Employee> hashMap;
    private final EmployeeSpecificController controller;

    public EmployeeSelector() {
        controller = new EmployeeSpecificController();
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (Employee employee : controller.fillSelectorByName(browser)) {
            String key = "(" + employee.getUsername() + ") " + employee.getLastname() + ", " + employee.getFirstname();
            hashMap.put(key, employee);
            list.add(key);
        }
    }

    public Employee getSelectedValue() {
        Employee employee = new EmployeeNull();

        if (!selection.isEmpty()) {
            employee = hashMap.get(selection);
        }

        return employee;
    }

}
