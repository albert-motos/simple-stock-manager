package com.development.simplestockmanager.web.view.search;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import com.development.simplestockmanager.web.object.component.selector.EmployeeSelector;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Base class for search view controller
 *
 * @author foxtrot
 */
abstract class BaseSearchView implements Serializable {

    protected InternationalizationController internationalizationController;
    protected Employee user;
    
    protected Date createdDateFrom;
    protected Date lastModifiedDateFrom;
    protected Date createdDateTo;
    protected Date lastModifiedDateTo;
    protected EmployeeSelector createdUser;
    protected EmployeeSelector lastModifiedUser;    
    
    public BaseSearchView() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new InternationalizationController(user.getLanguageType().getCode());
        
        createdUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        lastModifiedUser = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
    }

    /**
     * Main function of find view for update list with browser fields
     */
    abstract public void find();

    /**
     * Main function of find view for clear browser
     */
    abstract public void clear();
    
    protected void sendObjectToSession(String key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
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
