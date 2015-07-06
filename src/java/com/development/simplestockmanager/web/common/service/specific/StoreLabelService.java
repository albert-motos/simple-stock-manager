package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific store label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "storeLabel")
@SessionScoped
public class StoreLabelService implements Serializable {

    private final String attributes;
    private final String manager;
    private final String visibility;
    private final String enable;

    public StoreLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.STORE.ATTRIBUTES);
        manager = controller.getWord(CommonConstant.HEADER.STORE.MANAGER);
        visibility = controller.getWord(CommonConstant.HEADER.STORE.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.STORE);
    }

    public String getAttributes() {
        return attributes;
    }

    public String getManager() {
        return manager;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getEnable() {
        return enable;
    }

}
