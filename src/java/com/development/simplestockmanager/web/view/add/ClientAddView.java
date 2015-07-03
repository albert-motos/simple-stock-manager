package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.ClientValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Client object
 *
 * @author foxtrot
 */
@ManagedBean(name = "cliendAdd")
@ViewScoped 
public class ClientAddView extends BaseAddView {

    private final ClientValidator validator;
    private final ClientGeneralController controller;

    private final SexTypeSelector sexTypeSelector;
    private final Client client;

    public ClientAddView() {        
        validator = new ClientValidator(Constant.VALIDATOR.MODE.CREATE, user.getLanguageType().getCode());
        controller = new ClientGeneralController();
        
        client = new Client();
        sexTypeSelector = new SexTypeSelector(user.getLanguageType().getCode());
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        client.setSexType(sexTypeSelector.getSelectedValue());
        validator.setObject(client);

        if (validator.validate()) {
            client.setCreatedDate(new Date());
            client.setLastModifiedDate(new Date());
            client.setCreatedUser(user.getUsername());
            client.setLastModifiedUser(user.getUsername());

            Long id = controller.create(client);

            if (id == Constant.IDENTIFIER.INVALID) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Database server doesn't work properly"));
            } else {
                added = true;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Client [" + id + "] is created properly"));
            }
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public SexTypeSelector getSexTypeSelector() {
        return sexTypeSelector;
    }

    public Client getClient() {
        return client;
    }

}
