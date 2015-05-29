/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.type;

import com.simplestockmanager.data.controller.specific.EmployeeTypeSpecificController;
import com.simplestockmanager.persistence.EmployeeType;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class EmployeeTypeView {

    private List<EmployeeType> list;

    public EmployeeTypeView() {
        list = EmployeeTypeSpecificController.getAll();
    }
    
    public List<EmployeeType> getList() {
        return list;
    }

    public void setList(List<EmployeeType> list) {
        this.list = list;
    }

}
