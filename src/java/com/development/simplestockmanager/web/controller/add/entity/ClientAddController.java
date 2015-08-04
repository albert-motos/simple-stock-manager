package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.ClientCommonController;
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
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
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
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.ENTITY.CLIENT, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));

        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
