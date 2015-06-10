/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.controller.general.ProviderGeneralController;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class ProviderAddView implements AddView {

    private Provider provider;
    private boolean added;

    public ProviderAddView() {
        provider = new Provider();
        added = false;
    }

    @Override
    public void add() {
        
        if (validate()) {
            long id = ProviderGeneralController.create(provider.getName(), provider.getIdentifier(), provider.getPhone(), provider.getEmail(),
                    provider.getIsEnable(), new Date(), new Date());

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create provider right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Provider [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String fields_empty = "";

        fields_empty = fields_empty.concat((provider.getName().isEmpty() ? "Name " : ""));
        fields_empty = fields_empty.concat((provider.getIdentifier().isEmpty() ? "Identifier " : ""));
        fields_empty = fields_empty.concat((provider.getPhone().isEmpty() ? "Phone_number " : ""));
        fields_empty = fields_empty.concat((provider.getEmail().isEmpty() ? "Email " : ""));

        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

}
