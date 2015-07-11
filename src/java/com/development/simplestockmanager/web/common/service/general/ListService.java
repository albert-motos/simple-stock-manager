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
 * Service class for list internationalization functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "list")
@SessionScoped
public class ListService implements Serializable {

    private final String actions;
    private final String brand;
    private final String client;
    private final String employee;
    private final String product;
    private final String provider;
    private final String store;
    private final String status;
    private final String statusHidden;
    private final String statusVisible;
    private final String counter;
    
    private final String name;

    public ListService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.LABEL.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguageType().getCode());
        
        actions = controller.getWord(CommonConstant.LABEL.LIST.COLUMN.ACTIONS);
        brand = controller.getWord(CommonConstant.LABEL.LIST.HEADER.BRAND);
        client = controller.getWord(CommonConstant.LABEL.LIST.HEADER.CLIENT);
        employee = controller.getWord(CommonConstant.LABEL.LIST.HEADER.EMPLOYEE);
        product = controller.getWord(CommonConstant.LABEL.LIST.HEADER.PRODUCT);
        provider = controller.getWord(CommonConstant.LABEL.LIST.HEADER.PROVIDER);
        store = controller.getWord(CommonConstant.LABEL.LIST.HEADER.STORE);
        status = controller.getWord(CommonConstant.LABEL.LIST.COLUMN.STATUS.TEXT);
        statusHidden = controller.getWord(CommonConstant.LABEL.LIST.COLUMN.STATUS.HIDDEN);
        statusVisible = controller.getWord(CommonConstant.LABEL.LIST.COLUMN.STATUS.VISIBLE);
        
        counter = controller.getWord(CommonConstant.LABEL.LIST.COUNTER);
        
        name = controller.getWord(CommonConstant.LABEL.LIST.COLUMN.NAME);
    }

    public String getActions() {
        return actions;
    }

    public String getBrand() {
        return brand;
    }

    public String getClient() {
        return client;
    }

    public String getEmployee() {
        return employee;
    }

    public String getProduct() {
        return product;
    }

    public String getProvider() {
        return provider;
    }

    public String getStore() {
        return store;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getStatus(boolean enable) {
        return (enable ? statusVisible : statusHidden);
    }

    public String getCounter() {
        return counter;
    }
    
}
