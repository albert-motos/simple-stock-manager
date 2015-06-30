package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.web.object.Client;
import com.development.simplestockmanager.common.converter.ClientConverter;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.component.translator.ClientTranslator;
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
@ViewScoped
@ManagedBean
public class ClientAddView extends BaseAddView {

    private final ClientValidator validator;
    private final ClientGeneralController controller;
    private final ClientConverter converter;
    private final ClientTranslator translator;

    private final SexTypeSelector sexTypeSelector;
    private final Client client;

    public ClientAddView() {        
        validator = new ClientValidator(Constant.VALIDATOR.MODE.CREATE);
        controller = new ClientGeneralController();
        converter = new ClientConverter();
        translator = new ClientTranslator(user.getLanguageType().getCode());

        client = new Client();
        sexTypeSelector = new SexTypeSelector(user.getLanguageType().getCode());
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(sexTypeSelector.getSelectedValue().getId());
        client.setSexType(sexTypeSelector.getSelectedValue().getId());
        validator.setObject(client);

        if (validator.validate()) {
            com.development.simplestockmanager.business.persistence.Client businessObject = converter.getBusinessObject(client);
            businessObject.setCreatedDate(new Date());
            businessObject.setLastModifiedDate(new Date());
            businessObject.setCreatedUser(user.getUsername());
            businessObject.setLastModifiedUser(user.getUsername());

            Long id = controller.create(businessObject);

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

    public ClientTranslator getTranslator() {
        return translator;
    }

}
