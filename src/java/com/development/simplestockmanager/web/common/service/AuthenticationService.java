package com.development.simplestockmanager.web.common.service;

import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.Constant;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Service class for authentication functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "authentication")
@SessionScoped
public class AuthenticationService implements Serializable {

    private final EmployeeGeneralController generalController;
    private final EmployeeSpecificController specificController;

    private Employee employee;

    public AuthenticationService() {
        System.out.println("# " + new Date() + " | " + Constant.LOGGER.SERVICE.AUTHENTICATION.CONSTRUCTOR);
        
        generalController = new EmployeeGeneralController();
        specificController = new EmployeeSpecificController();
        employee = new EmployeeNull();
    }

    public void redirect() {
        System.out.println("# " + new Date() + " | " + Constant.LOGGER.SERVICE.AUTHENTICATION.REDIRECT);

        if (employee.getId() != Constant.IDENTIFIER.INVALID) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(Constant.WEB.INDEX);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void login() {
        System.out.println("#" + new Date() + " | " + Constant.LOGGER.SERVICE.AUTHENTICATION.LOGIN);

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        employee = specificController.getEmployeeByCredencials(employee.getUsername(), employee.getPassword());

        if (employee.getId() != Constant.IDENTIFIER.INVALID) {
            try {
                employee.setIsOnline(true);
                employee.setLastOnlineDate(new Date());
                employee.setSessionId(currentInstance.getExternalContext().getSessionId(true));
                if (generalController.update(employee) == Constant.UPDATE.FAILURE) {
                    throw new Exception();
                }

                currentInstance.getExternalContext().redirect(Constant.WEB.INDEX);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The username or password is wrong");
            currentInstance.addMessage(null, message);
        }
    }

    public void logout() {
        System.out.println("#" + new Date() + " | " + Constant.LOGGER.SERVICE.AUTHENTICATION.LOGOUT);

        try {
            employee.setIsOnline(false);

            if (generalController.update(employee) == Constant.UPDATE.FAILURE) {
                throw new Exception();
            }

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(Constant.WEB.LOGIN);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Employee getCurrentEmployee() {
        System.out.println("#" + new Date() + " | " + Constant.LOGGER.SERVICE.AUTHENTICATION.GET_CURRENT_EMPLOYEE);
        
        String session = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
        return specificController.getEmployeeBySession(session);
    }

    public Employee getEmployee() {
        return employee;
    }

}
