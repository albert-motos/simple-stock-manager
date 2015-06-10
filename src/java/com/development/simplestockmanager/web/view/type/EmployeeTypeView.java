/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.type;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeSpecificController;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@Deprecated
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
