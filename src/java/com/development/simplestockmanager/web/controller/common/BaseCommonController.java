package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.object.component.selector.EmployeeSelector;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;

/**
 * Base class for web controller
 *
 * @author foxtrot
 */
public class BaseCommonController extends SessionController implements Serializable {

    protected LanguageController languageController;
    protected Employee user;
    
    protected boolean action;
    
    protected FacesMessage.Severity severity;
    protected String summary;
    protected String detail;
    
    protected Date createdDateFrom;
    protected Date lastModifiedDateFrom;
    protected Date createdDateTo;
    protected Date lastModifiedDateTo;
    protected EmployeeSelector createdUser;
    protected EmployeeSelector lastModifiedUser;

    public BaseCommonController() {
        user = new AuthenticationService().getCurrentEmployee();
        languageController = new LanguageController(user.getLanguage().getCode());
        action = false;

        createdUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        lastModifiedUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
    }
    
    public void back() {
        new NavigationService().redirect(BusinessConstant.URL.INDEX);
    }

    public boolean isAction() {
        return action;
    }

    public Date getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(Date createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public Date getLastModifiedDateFrom() {
        return lastModifiedDateFrom;
    }

    public void setLastModifiedDateFrom(Date lastModifiedDateFrom) {
        this.lastModifiedDateFrom = lastModifiedDateFrom;
    }

    public Date getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(Date createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public Date getLastModifiedDateTo() {
        return lastModifiedDateTo;
    }

    public void setLastModifiedDateTo(Date lastModifiedDateTo) {
        this.lastModifiedDateTo = lastModifiedDateTo;
    }

    public EmployeeSelector getCreatedUser() {
        return createdUser;
    }

    public EmployeeSelector getLastModifiedUser() {
        return lastModifiedUser;
    }

}
