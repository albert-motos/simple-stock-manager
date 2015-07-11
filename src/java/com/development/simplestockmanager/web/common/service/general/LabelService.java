package com.development.simplestockmanager.web.common.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
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
    private final String name;
    private final String description;
    private final String identifier;
    private final String productType;
    private final String brand;
    private final String provider;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String employee;

    private final String nonSelection;

    private final String auditoryHeader;
    private final String auditoryBetween;
    private final String auditoryAnd;
    private final String auditoryCreatedUser;
    private final String auditoryLastModifiedUser;
    private final String auditoryCreatedDate;
    private final String auditoryLastModifiedDate;

    public LabelService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.LABEL.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguageType().getCode());

        firstname = controller.getWord(CommonConstant.LABEL.FIRSTNAME);
        lastname = controller.getWord(CommonConstant.LABEL.LASTNAME);
        sexType = controller.getWord(CommonConstant.LABEL.SEX_TYPE);
        bornDate = controller.getWord(CommonConstant.LABEL.BORN_DATE);
        phoneNumber = controller.getWord(CommonConstant.LABEL.PHONE_NUMBER);
        email = controller.getWord(CommonConstant.LABEL.EMAIL);
        usernamme = controller.getWord(CommonConstant.LABEL.USERNAME);
        password = controller.getWord(CommonConstant.LABEL.PASSWORD);
        languageType = controller.getWord(CommonConstant.LABEL.LANGUAGE_TYPE);
        employeeType = controller.getWord(CommonConstant.LABEL.EMPLOYEE_TYPE);
        name = controller.getWord(CommonConstant.LABEL.NAME);
        description = controller.getWord(CommonConstant.LABEL.DESCRIPTION);
        identifier = controller.getWord(CommonConstant.LABEL.IDENTIFIER);
        productType = controller.getWord(CommonConstant.LABEL.PRODUCT_TYPE);
        brand = controller.getWord(CommonConstant.LABEL.BRAND);
        provider = controller.getWord(CommonConstant.LABEL.PROVIDER);
        street = controller.getWord(CommonConstant.LABEL.STREET);
        city = controller.getWord(CommonConstant.LABEL.CITY);
        state = controller.getWord(CommonConstant.LABEL.STATE);
        country = controller.getWord(CommonConstant.LABEL.COUNTRY);
        employee = controller.getWord(CommonConstant.LABEL.EMPLOYEE);

        nonSelection = controller.getWord(CommonConstant.LABEL.NON_SELECTION);

        auditoryAnd = controller.getWord(CommonConstant.LABEL.AUDITORY.AND);
        auditoryBetween = controller.getWord(CommonConstant.LABEL.AUDITORY.BETWEEN);
        auditoryCreatedDate = controller.getWord(CommonConstant.LABEL.AUDITORY.CREATED_DATE);
        auditoryCreatedUser = controller.getWord(CommonConstant.LABEL.AUDITORY.CREATED_USER);
        auditoryHeader = controller.getWord(CommonConstant.LABEL.AUDITORY.HEADER);
        auditoryLastModifiedDate = controller.getWord(CommonConstant.LABEL.AUDITORY.LAST_MODIFIED_DATE);
        auditoryLastModifiedUser = controller.getWord(CommonConstant.LABEL.AUDITORY.LAST_MODIFIED_USER);
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNonSelection() {
        return nonSelection;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getProductType() {
        return productType;
    }

    public String getBrand() {
        return brand;
    }

    public String getProvider() {
        return provider;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getEmployee() {
        return employee;
    }

    public String getAuditoryHeader() {
        return auditoryHeader;
    }

    public String getAuditoryBetween() {
        return auditoryBetween;
    }

    public String getAuditoryAnd() {
        return auditoryAnd;
    }

    public String getAuditoryCreatedUser() {
        return auditoryCreatedUser;
    }

    public String getAuditoryLastModifiedUser() {
        return auditoryLastModifiedUser;
    }

    public String getAuditoryCreatedDate() {
        return auditoryCreatedDate;
    }

    public String getAuditoryLastModifiedDate() {
        return auditoryLastModifiedDate;
    }
}
