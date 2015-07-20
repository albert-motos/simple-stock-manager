package com.development.simplestockmanager.web.service.specific;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.service.general.AuthenticationService;
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
    private final String viewer;
    private final String list;
    private final String browser;

    public EmployeeLabelService() {
        System.out.println("# " + new Date() + " | " + EmployeeLabelService.class.getCanonicalName());

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        attributes = controller.getWord(CommonConstant.HEADER.EMPLOYE.ATTRIBUTES);
        credentials = controller.getWord(CommonConstant.HEADER.EMPLOYE.CREDENTIALS);
        visibility = controller.getWord(CommonConstant.HEADER.EMPLOYE.VISIBILITY);
        enable = controller.getWord(CommonConstant.LABEL.ENABLE.EMPLOYEE);
        viewer = controller.getWord(CommonConstant.HEADER.EMPLOYE.VIEWER);
        list = controller.getWord(CommonConstant.HEADER.EMPLOYE.LIST);
        browser = controller.getWord(CommonConstant.HEADER.EMPLOYE.BROWSER);
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