package com.development.simplestockmanager.web.service.specific.entity;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.service.general.AuthenticationService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific client label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandLabel")
@SessionScoped
public class BrandLabelService implements Serializable {

    private final String attributes;
    private final String visibility;
    private final String enable;
    private final String viewer;
    private final String list;
    private final String browser;

    public BrandLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        attributes = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
        visibility = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
        viewer = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
        list = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
        browser = controller.getWord(CommonConstant.LABEL.ENABLE.BRAND);
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
