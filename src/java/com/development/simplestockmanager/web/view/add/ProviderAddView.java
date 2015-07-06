package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.business.object.controller.general.ProviderGeneralController;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.object.validator.ProviderValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Provider object
 *
 * @author foxtrot
 */
@ManagedBean(name = "providerAdd")
@ViewScoped
public class ProviderAddView extends BaseAddView {

    private final ProviderValidator validator;
    private final ProviderGeneralController generalController;

    private final Provider provider;

    public ProviderAddView() {
        validator = new ProviderValidator(WebConstant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new ProviderGeneralController();

        provider = new Provider();
    }
    
    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();

        validator.setObject(provider);

        if (validator.validate()) {
            provider.setCreatedDate(new Date());
            provider.setLastModifiedDate(new Date());
            provider.setCreatedUser(user.getUsername());
            provider.setLastModifiedUser(user.getUsername());

            Long id = generalController.create(provider);

            Severity severity;
            String summary;
            String detail;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PROVIDER) + id +
                        internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));

        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public Provider getProvider() {
        return provider;
    }

}
