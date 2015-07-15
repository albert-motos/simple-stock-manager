package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific product label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "productLabel")
@SessionScoped
public class ProductLabelService implements Serializable {

    private final String attributes;
    private final String visibility;
    private final String enable;
    private final String viewer;
    private final String list;
    private final String browser;

    public ProductLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.PRODUCT.ATTRIBUTES);
        visibility = controller.getWord(CommonConstant.HEADER.PRODUCT.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.PRODUCT);
        viewer = controller.getWord(CommonConstant.HEADER.PRODUCT.VIEWER);
        list = controller.getWord(CommonConstant.HEADER.PRODUCT.LIST);
        browser = controller.getWord(CommonConstant.HEADER.PRODUCT.BROWSER);
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
