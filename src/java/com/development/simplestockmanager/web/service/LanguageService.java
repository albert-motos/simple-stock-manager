package com.development.simplestockmanager.web.service;

import com.development.simplestockmanager.business.object.controller.specific.LanguageSpecificController;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.Button;
import com.development.simplestockmanager.common.language.Header;
import com.development.simplestockmanager.common.language.Label;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.language.LanguageControllerManager;
import com.development.simplestockmanager.common.language.Page;
import com.development.simplestockmanager.common.language.Special;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Service class for internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "language")
@SessionScoped
public class LanguageService implements Serializable {
    
    private final String entity;
    private final String type;
    private final String relation;
    
    private final Header brand;
    private final Header client;
    private final Header employee;
    private final Header product;
    private final Header provider;
    private final Header store;
    private final Header employee_type;
    private final Header payment_type;
    private final Header price_type;
    private final Header product_type;
    private final Header sex_type;
    
    private final Page attributes;
    private final Page browser;
    private final Page credentials;
    private final Page list;
    private final Page manager;
    private final Page viewer;
    private final Page visibility;
    private final Page enable;
    
    private final Label label;
    private final Label column;
    private final Button button;
    private final Special special;
    
    public LanguageService() {
        LanguageController controller = LanguageControllerManager.getInstance().getController();
//        LanguageController controller = new LanguageController("en_US");

        entity = controller.getWord(CommonConstant.ENTITY.TEXT);
        type = controller.getWord(CommonConstant.TYPE.TEXT);
        relation = "null";
        
        brand = new Header(CommonConstant.ENTITY.BRAND);
        client = new Header(CommonConstant.ENTITY.CLIENT);
        employee = new Header(CommonConstant.ENTITY.EMPLOYEE);
        product = new Header(CommonConstant.ENTITY.PRODUCT);
        provider = new Header(CommonConstant.ENTITY.PROVIDER);
        store = new Header(CommonConstant.ENTITY.STORE);
        
        employee_type = new Header(CommonConstant.TYPE.EMPLOYEE_TYPE);
        payment_type = new Header(CommonConstant.TYPE.PAYMENT_TYPE);
        price_type = new Header(CommonConstant.TYPE.PRICE_TYPE);
        product_type = new Header(CommonConstant.TYPE.PRODUCT_TYPE);
        sex_type = new Header(CommonConstant.TYPE.SEX_TPE);
        
        attributes = new Page(controller.getWord(CommonConstant.PAGE.ATTRIBUTES));
        browser = new Page(controller.getWord(CommonConstant.PAGE.BROWSER));
        credentials = new Page(controller.getWord(CommonConstant.PAGE.CREDENTIALS));
        list = new Page(controller.getWord(CommonConstant.PAGE.LIST));
        manager = new Page(controller.getWord(CommonConstant.PAGE.MANAGER));
        viewer = new Page(controller.getWord(CommonConstant.PAGE.VIEWER));
        visibility = new Page(controller.getWord(CommonConstant.PAGE.VISIBILITY));
        enable = new Page(controller.getWord(CommonConstant.PAGE.ENABLE));
        
        label = new Label(controller.getWord(CommonConstant.LABEL.BASE));
        column = new Label(controller.getWord(CommonConstant.COLUMN.BASE));
        button = new Button();
        special = new Special();
    }

    public String getEntity() {
        return entity;
    }

    public String getType() {
        return type;
    }

    public String getRelation() {
        return relation;
    }

    public Header getBrand() {
        return brand;
    }

    public Header getClient() {
        return client;
    }

    public Header getEmployee() {
        return employee;
    }

    public Header getProduct() {
        return product;
    }

    public Header getProvider() {
        return provider;
    }

    public Header getStore() {
        return store;
    }

    public Header getEmployee_type() {
        return employee_type;
    }

    public Header getPayment_type() {
        return payment_type;
    }

    public Header getPrice_type() {
        return price_type;
    }

    public Header getProduct_type() {
        return product_type;
    }

    public Header getSex_type() {
        return sex_type;
    }

    public Page getAttributes() {
        return attributes;
    }

    public Page getBrowser() {
        return browser;
    }

    public Page getCredentials() {
        return credentials;
    }

    public Page getList() {
        return list;
    }

    public Page getManager() {
        return manager;
    }

    public Page getViewer() {
        return viewer;
    }

    public Page getVisibility() {
        return visibility;
    }

    public Page getEnable() {
        return enable;
    }

    public Label getLabel() {
        return label;
    }

    public Label getColumn() {
        return column;
    }

    public Button getButton() {
        return button;
    }

    public Special getSpecial() {
        return special;
    }

    public static Language getLanguage(String code) {
        LanguageSpecificController controller = new LanguageSpecificController();
        
        return controller.findByCode(code);
    }
    
}
