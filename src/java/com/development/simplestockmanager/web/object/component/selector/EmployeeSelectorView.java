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
public class EmployeeSelectorView implements SelectorView {

    private String browser;
    private HashMap<String, Employee> hashMap;
    private List<String> list;
    private String selection;

    public EmployeeSelectorView() {
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (Employee employee : EmployeeSpecificController.findByName(browser)) {
            String key = employee.getFirstName() + " " + employee.getLastName();
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

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

}
