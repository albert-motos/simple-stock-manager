package com.development.simplestockmanager.web.service.specific;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.service.general.AuthenticationService;
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
    private final String viewer;
    private final String list;
    private final String browser;

    public StoreLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.STORE.ATTRIBUTES);
        manager = controller.getWord(CommonConstant.HEADER.STORE.MANAGER);
        visibility = controller.getWord(CommonConstant.HEADER.STORE.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.STORE);
        viewer = controller.getWord(CommonConstant.HEADER.STORE.VIEWER);
        list = controller.getWord(CommonConstant.HEADER.STORE.LIST);
        browser = controller.getWord(CommonConstant.HEADER.STORE.BROWSER);
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

    public String getViewer() {
        return viewer;
    }

    public String getList() {
        return list;
    }

    public String getBrowser() {
        return browser;
    }

}
