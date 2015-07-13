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
    
    private final String status;
    private final String statusHidden;
    private final String statusVisible;
    private final String counter;
    
    private final String name;
    private final String firstname;
    private final String lastname;

    public ListService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.LABEL.CONSTRUCTOR);

        Employee user = new AuthenticationService().getCurrentEmployee();
        LanguageController controller = new LanguageController(user.getLanguageType().getCode());
        
        actions = controller.getWord(CommonConstant.LIST.COLUMN.ACTIONS);
        status = controller.getWord(CommonConstant.LIST.COLUMN.STATUS.TEXT);
        statusHidden = controller.getWord(CommonConstant.LIST.COLUMN.STATUS.HIDDEN);
        statusVisible = controller.getWord(CommonConstant.LIST.COLUMN.STATUS.VISIBLE);
        
        counter = controller.getWord(CommonConstant.LIST.COUNTER);
        
        name = controller.getWord(CommonConstant.LIST.COLUMN.NAME);
        firstname = controller.getWord(CommonConstant.LIST.COLUMN.FIRSTNAME);
        lastname = controller.getWord(CommonConstant.LIST.COLUMN.LASTNAME);
    }

    public String getActions() {
        return actions;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStatus(boolean enable) {
        return (enable ? statusVisible : statusHidden);
    }

    public String getCounter() {
        return counter;
    }

    public String getStatusHidden() {
        return statusHidden;
    }

    public String getStatusVisible() {
        return statusVisible;
    }
    
}
