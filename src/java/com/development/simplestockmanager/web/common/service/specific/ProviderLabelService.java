package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
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
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        attributes = controller.getWord(InternationalizationConstant.HEADER.PROVIDER.ATTRIBUTES);
        visibility = controller.getWord(InternationalizationConstant.HEADER.PROVIDER.VISIBILITY);
        enable = controller.getWord(InternationalizationConstant.LABEL.ENABLE.PROVIDER);
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
