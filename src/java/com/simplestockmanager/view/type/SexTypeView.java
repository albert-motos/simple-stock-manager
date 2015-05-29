/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.type;

import com.simplestockmanager.data.controller.specific.SexTypeSpecificController;
import com.simplestockmanager.data.nullpackage.SexTypeNull;
import com.simplestockmanager.persistence.SexType;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class SexTypeView {

    private List<SexType> list;

    public SexTypeView() {
        list = SexTypeSpecificController.getAll();
    }
    
    public List<SexType> getList() {
        return list;
    }

    public void setList(List<SexType> list) {
        this.list = list;
    }

}
