package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeSelector extends BaseSelector {

    private HashMap<String, Employee> hashMap;
    private EmployeeSpecificController specificController;

    private EmployeeSelector() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    public EmployeeSelector(long mode, EmployeeSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;
    }

    public EmployeeSelector(long mode, Employee employee, EmployeeSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;
        
        String key = "(" + employee.getUsername() + ") " + employee.getLastname() + ", " + employee.getFirstname();
        hashMap.put(key, employee);
        list.add(key);
        selection = key;
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        List<Employee> employeeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            employeeList = specificController.fillSelector();
        } else {
            employeeList = specificController.fillSelectorByName(browser);
        }

        for (Employee employee : employeeList) {
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
