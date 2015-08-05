package com.development.simplestockmanager.common.web.controller.common;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.AuthenticationService;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.web.object.selector.entity.EmployeeSelector;
import com.development.simplestockmanager.web.service.MessageService;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;

/**
 * Base class for web controller
 *
 * @author foxtrot
 */
public class BaseCommonController extends SessionController implements Serializable {

    protected MessageService messageService;
    protected Employee user;
    
    protected boolean action;
    protected long status;
    
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
        messageService = new MessageService();
        action = false;

        createdUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        lastModifiedUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
    }
    
    public void clear() {
        createdUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        lastModifiedUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
    }
    
    public void back() {
        new NavigationService().redirect(WebConstant.WEB.INDEX);
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

    public long getStatus() {
        return status;
    }

}
