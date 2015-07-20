package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.ProviderCommonController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Provider object
 *
 * @author foxtrot
 */
@ManagedBean(name = "providerAdd")
@ViewScoped
public class ProviderAddController extends ProviderCommonController implements AddController {

    public ProviderAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        provider = new Provider();
    }

    @Override
    public void add() {
        validator.setObject(provider);
        
        if (validator.validate()) {
            provider.setCreatedDate(new Date());
            provider.setLastModifiedDate(new Date());
            provider.setCreatedUser(user);
            provider.setLastModifiedUser(user);

            Long id = generalController.create(provider);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PROVIDER) + id +
                        languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
