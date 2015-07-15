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
 * Service class for menu internationalization functionality
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
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.MENU.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguage().getCode());

        entity = controller.getWord(CommonConstant.MENU.ENTITY.TEXT);
        brand = controller.getWord(CommonConstant.MENU.ENTITY.BRAND.TEXT);
        brandCreate = controller.getWord(CommonConstant.MENU.ENTITY.BRAND.CREATE);
        brandSearch = controller.getWord(CommonConstant.MENU.ENTITY.BRAND.SEARCH);
        client = controller.getWord(CommonConstant.MENU.ENTITY.CLIENT.TEXT);
        clientCreate = controller.getWord(CommonConstant.MENU.ENTITY.CLIENT.CREATE);
        clientSearch = controller.getWord(CommonConstant.MENU.ENTITY.CLIENT.SEARCH);
        employee = controller.getWord(CommonConstant.MENU.ENTITY.EMPLOYEE.TEXT);
        employeeCreate = controller.getWord(CommonConstant.MENU.ENTITY.EMPLOYEE.CREATE);
        employeeSearch = controller.getWord(CommonConstant.MENU.ENTITY.EMPLOYEE.SEARCH);
        product = controller.getWord(CommonConstant.MENU.ENTITY.PRODUCT.TEXT);
        productCreate = controller.getWord(CommonConstant.MENU.ENTITY.PRODUCT.CREATE);
        productSearch = controller.getWord(CommonConstant.MENU.ENTITY.PRODUCT.SEARCH);
        provider = controller.getWord(CommonConstant.MENU.ENTITY.PROVIDER.TEXT);
        providerCreate = controller.getWord(CommonConstant.MENU.ENTITY.PROVIDER.CREATE);
        providerSearch = controller.getWord(CommonConstant.MENU.ENTITY.PROVIDER.SEARCH);
        store = controller.getWord(CommonConstant.MENU.ENTITY.STORE.TEXT);
        storeCreate = controller.getWord(CommonConstant.MENU.ENTITY.STORE.CREATE);
        storeSearch = controller.getWord(CommonConstant.MENU.ENTITY.STORE.SEARCH);
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
