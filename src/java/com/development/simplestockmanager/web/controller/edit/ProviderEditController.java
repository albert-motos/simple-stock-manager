package com.development.simplestockmanager.web.controller.edit;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.ProviderCommonController;
import com.development.simplestockmanager.web.controller.common.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Provider object
 *
 * @author foxtrot
 */
@ManagedBean(name = "providerEdit")
@ViewScoped
public class ProviderEditController extends ProviderCommonController implements EditController {

    private Provider baseProvider;

    public ProviderEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            provider = (Provider) receiveObjectFromSession(WebConstant.SESSION.CLIENT);
            baseProvider = new Provider(provider);
        } catch (Exception e) {
            back();
        }
    }
    
    @Override
    public void edit() {
        if (provider.equals(baseProvider)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PROVIDER) + provider.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(provider);

            if (validator.validate()) {
                provider.setLastModifiedDate(new Date());
                provider.setLastModifiedUser(user);

                Long status = generalController.update(provider);

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PROVIDER) + provider.getId()
                            + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.EDIT);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.PROVIDER);
    }
    
}
