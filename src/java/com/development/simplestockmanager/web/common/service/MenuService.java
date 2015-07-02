package com.development.simplestockmanager.web.common.service;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.Constant;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author foxtrot
 */
@ManagedBean(name = "menu")
@SessionScoped
public class MenuService implements Serializable {
    
    private final String entity;
    private final String brand;
    private final String brandCreate;
    private final String brandSearch;
    private final String client;
    private final String clientCreate;
    private final String clientSearch;
    private final String employee;
    private final String employeeCreate;
    private final String employeeSearch;
    private final String product;
    private final String productCreate;
    private final String productSearch;
    private final String provider;
    private final String providerCreate;
    private final String providerSearch;
    private final String store;
    private final String storeCreate;
    private final String storeSearch;
    

    public MenuService() {
        System.out.println("# " + new Date() + " | " + Constant.LOGGER.SERVICE.MENU.CONSTRUCTOR);
        
        Employee user = new AuthenticationService().getCurrentEmployee();
        InternationalizationController controller = new InternationalizationController(user.getLanguageType().getCode());
        
        entity = controller.getWord(InternationalizationConstant.MENU.ENTITY.TEXT);
        brand = controller.getWord(InternationalizationConstant.MENU.ENTITY.BRAND.TEXT);
        brandCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.BRAND.CREATE);
        brandSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.BRAND.SEARCH);
        client = controller.getWord(InternationalizationConstant.MENU.ENTITY.CLIENT.TEXT);
        clientCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.CLIENT.CREATE);
        clientSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.CLIENT.SEARCH);
        employee = controller.getWord(InternationalizationConstant.MENU.ENTITY.EMPLOYEE.TEXT);
        employeeCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.EMPLOYEE.CREATE);
        employeeSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.EMPLOYEE.SEARCH);
        product = controller.getWord(InternationalizationConstant.MENU.ENTITY.PRODUCT.TEXT);
        productCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.PRODUCT.CREATE);
        productSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.PRODUCT.SEARCH);
        provider = controller.getWord(InternationalizationConstant.MENU.ENTITY.PROVIDER.TEXT);
        providerCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.PROVIDER.CREATE);
        providerSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.PROVIDER.SEARCH);
        store = controller.getWord(InternationalizationConstant.MENU.ENTITY.STORE.TEXT);
        storeCreate = controller.getWord(InternationalizationConstant.MENU.ENTITY.STORE.CREATE);
        storeSearch = controller.getWord(InternationalizationConstant.MENU.ENTITY.STORE.SEARCH);
    }

    public String getEntity() {
        return entity;
    }

    public String getBrand() {
        return brand;
    }

    public String getBrandCreate() {
        return brandCreate;
    }

    public String getBrandSearch() {
        return brandSearch;
    }

    public String getClient() {
        return client;
    }

    public String getClientCreate() {
        return clientCreate;
    }

    public String getClientSearch() {
        return clientSearch;
    }

    public String getEmployee() {
        return employee;
    }

    public String getEmployeeCreate() {
        return employeeCreate;
    }

    public String getEmployeeSearch() {
        return employeeSearch;
    }

    public String getProduct() {
        return product;
    }

    public String getProductCreate() {
        return productCreate;
    }

    public String getProductSearch() {
        return productSearch;
    }

    public String getProvider() {
        return provider;
    }

    public String getProviderCreate() {
        return providerCreate;
    }

    public String getProviderSearch() {
        return providerSearch;
    }

    public String getStore() {
        return store;
    }

    public String getStoreCreate() {
        return storeCreate;
    }

    public String getStoreSearch() {
        return storeSearch;
    }

}
