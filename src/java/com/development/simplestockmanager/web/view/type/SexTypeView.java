/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.type;

import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@Deprecated
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
