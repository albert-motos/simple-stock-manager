/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.common.converter;

import com.development.simplestockmanager.business.object.controller.general.SexTypeGeneralController;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.business.persistence.SexType;

/**
 *
 * @author foxtrot
 */
public class ClientConverter {
    
    SexTypeGeneralController sexTypeGeneralController;

    public ClientConverter() {
        sexTypeGeneralController = new SexTypeGeneralController();
    }
    
    public com.development.simplestockmanager.business.persistence.Client getBusinessObject(com.development.simplestockmanager.web.object.Client web) {
        com.development.simplestockmanager.business.persistence.Client business = new Client();
        business.setBornDate(web.getBornDate());
        business.setCreatedDate(web.getCreatedDate());
        business.setCreatedUser(web.getCreatedUser());
        business.setEmail(web.getEmail());
        business.setEnable(web.isEnable());
        business.setFirstname(web.getFirstname());
        business.setId(web.getId());
        business.setLastModifiedDate(web.getLastModifiedDate());
        business.setLastModifiedUser(web.getLastModifiedUser());
        business.setLastname(web.getLastname());
        business.setPhone(web.getPhone());
        business.setSexType(sexTypeGeneralController.read(new SexType(web.getSexType())));
        
        return business;
    }
}
