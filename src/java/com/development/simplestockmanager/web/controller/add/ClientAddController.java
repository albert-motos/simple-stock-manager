package com.development.simplestockmanager.web.controller.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.ClientCommonController;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for Client object
 *
 * @author foxtrot
 */
@ManagedBean(name = "clientAdd")
@ViewScoped
public class ClientAddController extends ClientCommonController implements AddController {

    public ClientAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        client = new Client();
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage());
    }

    @Override
    public void add() {
        client.setSexType(sexTypeSelector.getSelectedValue());
        validator.setObject(client);

        if (validator.validate()) {
            client.setCreatedDate(new Date());
            client.setLastModifiedDate(new Date());
            client.setCreatedUser(user);
            client.setLastModifiedUser(user);

            Long id = generalController.create(client);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.CLIENT) + id
                        + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));

        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
