package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.ClientValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
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
    private final ClientGeneralController generalController;

    private final SexTypeSelector sexTypeSelector;
    private final Client client;

    public ClientAddView() {
        validator = new ClientValidator(Constant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new ClientGeneralController();

        client = new Client();
        sexTypeSelector = new SexTypeSelector(internationalizationController.getLanguage());
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

            Long id = generalController.create(client);

            Severity severity;
            String summary;
            String detail;

            if (id == Constant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.OBJECT.CLIENT) + id +
                        internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));

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
