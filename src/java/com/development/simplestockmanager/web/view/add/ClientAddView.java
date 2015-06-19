/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.business.object.controller.general.SexTypeGeneralController;
import com.development.simplestockmanager.business.persistence.old.Client;
import com.development.simplestockmanager.business.common.Constant;
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

    private Client client;
    private String sexTypeSelection;
    private boolean added;

    public ClientAddView() {
        client = new Client();
        added = false;
    }

    @Override
    public void add() {
        
        if (validate()) {
            long id = ClientGeneralController.create(client.getFirstName(), client.getLastName(), client.getBornDate(),
                    SexTypeGeneralController.create(sexTypeSelection), client.getPhone(), client.getEmail(), client.getIsEnable(), new Date(), new Date());

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create client right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Client [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String fields_empty = "";

        fields_empty = fields_empty.concat((client.getFirstName().isEmpty() ? "First_name " : ""));
        fields_empty = fields_empty.concat((client.getLastName().isEmpty() ? "Last_name " : ""));
        fields_empty = fields_empty.concat((client.getPhone().isEmpty() ? "Phone_number " : ""));
        fields_empty = fields_empty.concat((client.getEmail().isEmpty() ? "Email " : ""));

        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        if (client.getBornDate() == null) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The born_date is not indicated"));
        } else {
            if (client.getBornDate().after(new Date())) {
                currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The born_date is incorrect, the client don't born yet"));
            }
        }

        if (sexTypeSelection.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The sex_type_selector is not indicated"));
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public String getSexTypeSelection() {
        return sexTypeSelection;
    }

    public void setSexTypeSelection(String sexTypeSelection) {
        this.sexTypeSelection = sexTypeSelection;
    }

}
