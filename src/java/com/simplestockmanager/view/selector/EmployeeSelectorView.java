/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.selector;

import com.simplestockmanager.data.controller.specific.EmployeeSpecificController;
import com.simplestockmanager.data.nullpackage.EmployeeNull;
import com.simplestockmanager.persistence.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@ViewScoped
public class EmployeeSelectorView implements SelectorView {

    private String browser;
    private List<Employee> list;
    private String item;

    public EmployeeSelectorView() {
        System.out.println("#init");
        list = new ArrayList<>();
    }

    @Override
    public void find() {
        list = EmployeeSpecificController.findByName(browser);
    }
    
    @Override
    public void reset() {
        browser = "";
        item = "";
    }
    
    public static Employee getValue() {
        new EmployeeSelectorView().reset();
        
        return new EmployeeNull();
    }
    
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
