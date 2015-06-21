/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.web.object.Client;
import com.development.simplestockmanager.common.converter.ClientConverter;
import com.development.simplestockmanager.web.object.component.selector.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.ClientValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class ClientAddView implements AddView {

    private FacesContext facesContext;
    private ClientValidator validator;
    private ClientGeneralController controller;
    private ClientConverter converter;
    
    private SexTypeSelector sexTypeSelector;
    private Client client;
    private boolean added;

    public ClientAddView() {
        facesContext = FacesContext.getCurrentInstance();
        validator = new ClientValidator();
        controller = new ClientGeneralController();
        converter = new ClientConverter();
        
        client = new Client();
        sexTypeSelector = new SexTypeSelector();
        added = false;
    }

    @Override
    public void add() {
        validator.setObject(client);
        
        if (validator.validate()) {
            client.setSexType(sexTypeSelector.getSelectedValue().getId());
            com.development.simplestockmanager.business.persistence.Client businessObject = converter.getBusinessObject(client);
            businessObject.setCreatedDate(new Date());
            businessObject.setLastModifiedDate(new Date());
            Long id = controller.create(businessObject);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Client [" + id + "] is created properly"));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                facesContext.addMessage(null, message);
            }
        }
    }

}
