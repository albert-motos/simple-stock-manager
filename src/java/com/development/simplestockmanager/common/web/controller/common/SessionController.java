package com.development.simplestockmanager.common.web.controller.common;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class for web session controller
 *
 * @author foxtrot
 */
public class SessionController {

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Object receiveObjectFromSession(String key) {
        return getSession().getAttribute(key);
    }

    protected void removeObjectFromSession(String key) {
        getSession().removeAttribute(key);
    }

    protected void sendObjectToSession(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    private HttpSession getSession() {
        HttpServletRequest request = (HttpServletRequest) getContext().getExternalContext().getRequest();
        return request.getSession();
    }
}
