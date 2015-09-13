package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;

/**
 *
 * @author foxtrot
 */
public class Label {

    private final String firstname;
    private final String lastname;
    private final String sex_type;
    private final String born_date;
    private final String phone_number;
    private final String email;
    private final String username;
    private final String password;
    private final String language;
    private final String employee_type;
    private final String name;
    private final String description;
    private final String identifier;
    private final String product_type;
    private final String brand;
    private final String provider;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String employee;
    private final String store;
    private final String product;
    
    private final String amount;
    private final String actual_amount;
    private final String total_amount;
    private final String note;
    
    private final String type;
    private final String translation_es_es;
    private final String translation_ca_es;
    private final String translation_en_us;
    private final String translation;
    
    private final String status;

    public Label(String base) {
         LanguageController controller = LanguageControllerManager.getInstance().getController();
        
        firstname = replace(base, controller.getWord(CommonConstant.LABEL.FIRSTNAME));
        lastname = replace(base, controller.getWord(CommonConstant.LABEL.LASTNAME));
        sex_type = replace(base, controller.getWord(CommonConstant.LABEL.SEX_TYPE));
        born_date = replace(base, controller.getWord(CommonConstant.LABEL.BORN_DATE));
        phone_number = replace(base, controller.getWord(CommonConstant.LABEL.PHONE_NUMBER));
        email = replace(base, controller.getWord(CommonConstant.LABEL.EMAIL));
        username = replace(base, controller.getWord(CommonConstant.LABEL.USERNAME));
        password = replace(base, controller.getWord(CommonConstant.LABEL.PASSWORD));
        language = replace(base, controller.getWord(CommonConstant.LABEL.LANGUAGE_TYPE));
        employee_type = replace(base, controller.getWord(CommonConstant.LABEL.EMPLOYEE_TYPE));
        name = replace(base, controller.getWord(CommonConstant.LABEL.NAME));
        description = replace(base, controller.getWord(CommonConstant.LABEL.DESCRIPTION));
        identifier = replace(base, controller.getWord(CommonConstant.LABEL.IDENTIFIER));
        product_type = replace(base, controller.getWord(CommonConstant.LABEL.PRODUCT_TYPE));
        brand = replace(base, controller.getWord(CommonConstant.LABEL.BRAND));
        provider = replace(base, controller.getWord(CommonConstant.LABEL.PROVIDER));
        street = replace(base, controller.getWord(CommonConstant.LABEL.STREET));
        city = replace(base, controller.getWord(CommonConstant.LABEL.CITY));
        state = replace(base, controller.getWord(CommonConstant.LABEL.STATE));
        country = replace(base, controller.getWord(CommonConstant.LABEL.COUNTRY));
        employee = replace(base, controller.getWord(CommonConstant.LABEL.EMPLOYEE));
        store = replace(base, controller.getWord(CommonConstant.LABEL.STORE));
        product = replace(base, controller.getWord(CommonConstant.LABEL.PRODUCT));
        
        amount = replace(base, controller.getWord(CommonConstant.LABEL.AMOUNT));
        actual_amount = replace(base, controller.getWord(CommonConstant.LABEL.ACTUAL_AMOUNT));
        total_amount = replace(base, controller.getWord(CommonConstant.LABEL.TOTAL_AMOUNT));
        note = replace(base, controller.getWord(CommonConstant.LABEL.NOTE));
        
        type = replace(base, controller.getWord(CommonConstant.LABEL.TYPE));
        translation_ca_es = replace(base, controller.getWord(CommonConstant.LABEL.TRANSLATION.CA_ES));
        translation_en_us = replace(base, controller.getWord(CommonConstant.LABEL.TRANSLATION.EN_US));
        translation_es_es = replace(base, controller.getWord(CommonConstant.LABEL.TRANSLATION.ES_ES));
        translation = replace(base, controller.getWord(CommonConstant.LABEL.TRANSLATION.TEXT));
        
        status = replace(base, controller.getWord(CommonConstant.LABEL.STATUS));
    }

    private String replace(String base, String type) {        
        return base.replace(CommonConstant.VARIANT.TYPE.CODE, type);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSex_type() {
        return sex_type;
    }

    public String getBorn_date() {
        return born_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getProduct_type() {
        return product_type;
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

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTranslation_es_es() {
        return translation_es_es;
    }

    public String getTranslation_ca_es() {
        return translation_ca_es;
    }

    public String getTranslation_en_us() {
        return translation_en_us;
    }

    public String getTranslation() {
        return translation;
    }

    public String getStore() {
        return store;
    }

    public String getProduct() {
        return product;
    }

    public String getAmount() {
        return amount;
    }

    public String getActual_amount() {
        return actual_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getNote() {
        return note;
    }

}
