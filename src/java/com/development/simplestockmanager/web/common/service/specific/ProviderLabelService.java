package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific provider label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "providerLabel")
@SessionScoped
public class ProviderLabelService implements Serializable {

    private final String attributes;
    private final String visibility;
    private final String enable;

    public ProviderLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguageType().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.PROVIDER.ATTRIBUTES);
        visibility = controller.getWord(CommonConstant.HEADER.PROVIDER.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.PROVIDER);
    }

    public String getAttributes() {
        return attributes;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getEnable() {
        return enable;
    }

}
