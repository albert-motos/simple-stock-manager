package com.development.simplestockmanager.web.service.specific.type;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.service.general.AuthenticationService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for specific employeeType label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeTypeLabel")
@SessionScoped
public class EmployeeTypeLabelService implements Serializable {

//    private final String attributes;
//    private final String translation;
//    private final String visibility;
//    private final String enable;
//    private final String viewer;
//    private final String list;
//    private final String browser;
//
//    public EmployeeTypeLabelService() {
//        Employee user = new AuthenticationService().getCurrentEmployee();
//        LanguageController controller = new LanguageController(user.getLanguage().getCode());
//
//        attributes = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.ATTRIBUTES);
//        translation = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.ATTRIBUTES);
//        visibility = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.VISIBILITY);
//        enable = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.BROWSER);
//        viewer = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.VIEWER);
//        list = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.LIST);
//        browser = controller.getWord(CommonConstant.HEADER.TYPE.EMPLOYEE_TYPE.BROWSER);
//    }
//
//    public String getAttributes() {
//        return attributes;
//    }
//
//    public String getVisibility() {
//        return visibility;
//    }
//
//    public String getEnable() {
//        return enable;
//    }
//
//    public String getViewer() {
//        return viewer;
//    }
//
//    public String getList() {
//        return list;
//    }
//
//    public String getBrowser() {
//        return browser;
//    }

}
