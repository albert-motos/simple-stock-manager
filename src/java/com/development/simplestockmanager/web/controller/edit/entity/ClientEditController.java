package com.development.simplestockmanager.web.controller.edit.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.ClientCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "clientEdit")
@ViewScoped
public class ClientEditController extends ClientCommonController implements EditController {

    private Client baseClient;

    public ClientEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            client = (Client) receiveObjectFromSession(WebConstant.SESSION.CLIENT);
            baseClient = new Client(client);
        } catch (Exception e) {
            back();
        }
        
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, client.getSexType());
    }
    
    @Override
    public void edit() {
        client.setSexType(sexTypeSelector.getSelectedValue());
        
        if (client.equals(baseClient)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.ENTITY.CLIENT, client.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(client);

            if (validator.validate()) {
                client.setLastModifiedDate(new Date());
                client.setLastModifiedUser(user);

                Long feedback = generalController.update(client);

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.ENTITY.CLIENT, client.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }
    
    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.ENTITY.CLIENT);
    }
    
}
