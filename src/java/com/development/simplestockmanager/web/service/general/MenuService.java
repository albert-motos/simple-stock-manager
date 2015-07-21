package com.development.simplestockmanager.web.service.general;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
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
    
    private final String type;
    private final String employeeType;
    private final String employeeTypeCreate;
    private final String employeeTypeSearch;
    private final String paymentType;
    private final String paymentTypeCreate;
    private final String paymentTypeSearch;
    private final String priceType;
    private final String priceTypeCreate;
    private final String priceTypeSearch;
    private final String productType;
    private final String productTypeCreate;
    private final String productTypeSearch;
    private final String sexType;
    private final String sexTypeCreate;
    private final String sexTypeSearch;

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
        
        type = controller.getWord(CommonConstant.MENU.TYPE.TEXT);
        employeeType = controller.getWord(CommonConstant.MENU.TYPE.EMPLOYEE_TYPE.TEXT);
        employeeTypeCreate = controller.getWord(CommonConstant.MENU.TYPE.EMPLOYEE_TYPE.CREATE);
        employeeTypeSearch = controller.getWord(CommonConstant.MENU.TYPE.EMPLOYEE_TYPE.SEARCH);
        paymentType = controller.getWord(CommonConstant.MENU.TYPE.PAYMENT_TYPE.TEXT);
        paymentTypeCreate = controller.getWord(CommonConstant.MENU.TYPE.PAYMENT_TYPE.CREATE);
        paymentTypeSearch = controller.getWord(CommonConstant.MENU.TYPE.PAYMENT_TYPE.SEARCH);
        priceType = controller.getWord(CommonConstant.MENU.TYPE.PRICE_TYPE.TEXT);
        priceTypeCreate = controller.getWord(CommonConstant.MENU.TYPE.PRICE_TYPE.CREATE);
        priceTypeSearch = controller.getWord(CommonConstant.MENU.TYPE.PRICE_TYPE.SEARCH);
        productType = controller.getWord(CommonConstant.MENU.TYPE.PRODUCT_TYPE.TEXT);
        productTypeCreate = controller.getWord(CommonConstant.MENU.TYPE.PRODUCT_TYPE.CREATE);
        productTypeSearch = controller.getWord(CommonConstant.MENU.TYPE.PRODUCT_TYPE.SEARCH);
        sexType = controller.getWord(CommonConstant.MENU.TYPE.SEX_TYPE.TEXT);
        sexTypeCreate = controller.getWord(CommonConstant.MENU.TYPE.SEX_TYPE.CREATE);
        sexTypeSearch = controller.getWord(CommonConstant.MENU.TYPE.SEX_TYPE.SEARCH);
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

    public String getType() {
        return type;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getEmployeeTypeCreate() {
        return employeeTypeCreate;
    }

    public String getEmployeeTypeSearch() {
        return employeeTypeSearch;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentTypeCreate() {
        return paymentTypeCreate;
    }

    public String getPaymentTypeSearch() {
        return paymentTypeSearch;
    }

    public String getPriceType() {
        return priceType;
    }

    public String getPriceTypeCreate() {
        return priceTypeCreate;
    }

    public String getPriceTypeSearch() {
        return priceTypeSearch;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductTypeCreate() {
        return productTypeCreate;
    }

    public String getProductTypeSearch() {
        return productTypeSearch;
    }

    public String getSexType() {
        return sexType;
    }

    public String getSexTypeCreate() {
        return sexTypeCreate;
    }

    public String getSexTypeSearch() {
        return sexTypeSearch;
    }

}
