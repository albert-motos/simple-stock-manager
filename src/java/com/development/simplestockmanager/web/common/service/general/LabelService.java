package com.development.simplestockmanager.web.common.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.Constant;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for label internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "label")
@SessionScoped
public class LabelService implements Serializable {

    private final String firstname;
    private final String lastname;
    private final String sexType;
    private final String bornDate;
    private final String phoneNumber;
    private final String email;
    private final String usernamme;
    private final String password;
    private final String languageType;
    private final String employeeType;
    private final String nonSelection;

    public LabelService() {
        System.out.println("# " + new Date() + " | " + Constant.LOGGER.SERVICE.LABEL.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());

        firstname = controller.getWord(InternationalizationConstant.LABEL.FIRSTNAME);
        lastname = controller.getWord(InternationalizationConstant.LABEL.LASTNAME);
        sexType = controller.getWord(InternationalizationConstant.LABEL.SEX_TYPE);
        bornDate = controller.getWord(InternationalizationConstant.LABEL.BORN_DATE);
        phoneNumber = controller.getWord(InternationalizationConstant.LABEL.PHONE_NUMBER);
        email = controller.getWord(InternationalizationConstant.LABEL.EMAIL);
        usernamme = controller.getWord(InternationalizationConstant.LABEL.USERNAME);
        password = controller.getWord(InternationalizationConstant.LABEL.PASSWORD);
        languageType = controller.getWord(InternationalizationConstant.LABEL.LANGUAGE_TYPE);
        employeeType = controller.getWord(InternationalizationConstant.LABEL.EMPLOYEE_TYPE);
        nonSelection = controller.getWord(InternationalizationConstant.LABEL.NON_SELECTION);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSexType() {
        return sexType;
    }

    public String getBornDate() {
        return bornDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUsernamme() {
        return usernamme;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguageType() {
        return languageType;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getNonSelection() {
        return nonSelection;
    }

}
