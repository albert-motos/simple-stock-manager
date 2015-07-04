package com.development.simplestockmanager.web.common.service.specific;

import com.development.simplestockmanager.web.common.service.general.*;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific employee label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeLabel")
@SessionScoped
public class EmployeeLabelService implements Serializable {

    private final String attributes;
    private final String credentials;
    private final String visibility;
    private final String enable;

    public EmployeeLabelService() {
        System.out.println("# " + new Date() + " | " + EmployeeLabelService.class.getCanonicalName());

        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        attributes = controller.getWord(InternationalizationConstant.HEADER.EMPLOYE.ATTRIBUTES);
        credentials = controller.getWord(InternationalizationConstant.HEADER.EMPLOYE.CREDENTIALS);
        visibility = controller.getWord(InternationalizationConstant.HEADER.EMPLOYE.VISIBILITY);
        enable = controller.getWord(InternationalizationConstant.LABEL.ENABLE.EMPLOYEE);
    }

    public String getAttributes() {
        return attributes;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getEnable() {
        return enable;
    }

}