/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author foxtrot
 */
public class SexTypeSelector extends BaseSelector  {

    private HashMap<String, SexType> hashMap;
    private SexTypeSpecificController controller;

    public SexTypeSelector() {
        controller = new SexTypeSpecificController();
    }
    
    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        
        
        for (SexType sexType : controller.getAll()) {
            String key = sexType.getType();
            hashMap.put(key, sexType);
            list.add(key);
        }
    }

    public SexType getSelectedValue() {
        SexType sexType = new SexTypeNull();

        if (!selection.isEmpty()) {
            sexType = hashMap.get(selection);
        }

        return sexType;
    }
    
}
