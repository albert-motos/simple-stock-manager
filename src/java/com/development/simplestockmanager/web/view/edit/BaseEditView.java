package com.development.simplestockmanager.web.view.edit;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Base class for search view controller
 *
 * @author foxtrot
 */
abstract class BaseEditView implements Serializable {

    protected InternationalizationController internationalizationController;
    protected Employee user;

    public BaseEditView() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new InternationalizationController(user.getLanguageType().getCode());
    }

    /**
     * Main function of edit view
     */
    abstract public void edit();

    /**
     * Function for back to search view
     */
    abstract public void back();
    
    private HttpSession getSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getSession();
    }
    
    protected Object receiveObjectFromSession(String key) {
        return getSession().getAttribute(key);
    }
    
    protected void removeObjectFromSession(String key) {
        getSession().removeAttribute(key);
    }
}
