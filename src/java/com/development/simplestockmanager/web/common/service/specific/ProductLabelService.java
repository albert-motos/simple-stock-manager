package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
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

    public ProductLabelService() {
        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.PRODUCT.ATTRIBUTES);
        visibility = controller.getWord(CommonConstant.HEADER.PRODUCT.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.PRODUCT);
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
