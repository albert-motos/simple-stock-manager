package com.development.simplestockmanager.web.service.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.language.LanguageControllerManager;
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

    private final NavigationService navigation;
    private final EmployeeGeneralController generalController;
    private final EmployeeSpecificController specificController;

    private Employee employee;

    public AuthenticationService() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.AUTHENTICATION.CONSTRUCTOR);

        navigation = new NavigationService();
        generalController = new EmployeeGeneralController();
        specificController = new EmployeeSpecificController();
        employee = new EmployeeNull();
    }

    public void redirect() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.AUTHENTICATION.REDIRECT);

        if (employee.getId() != BusinessConstant.IDENTIFIER.INVALID) {
            navigation.redirect(WebConstant.WEB.INDEX);
        }
    }

    public void login() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.AUTHENTICATION.LOGIN);

        employee = specificController.findByCredencials(employee.getUsername(), employee.getPassword());
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage message = null;

        try {
            if (employee.getId() == BusinessConstant.IDENTIFIER.INVALID) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The username or password is wrong");
                throw new Exception();
            }

            String session = currentInstance.getExternalContext().getSessionId(false);
            
            employee.setIsOnline(true);
            employee.setLastOnlineDate(new Date());
            employee.setSessionId(session);

            if (generalController.update(employee) == BusinessConstant.UPDATE.FAILURE) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The server don't work properly");
                throw new Exception();
            }
            
            LanguageControllerManager.getInstance().addSession(session, employee.getLanguage().getCode());

            navigation.redirect(WebConstant.WEB.INDEX);
        } catch (Exception e) {
            currentInstance.addMessage(null, message);
        }
    }

    public void logout() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.AUTHENTICATION.LOGOUT);

        try {
            employee.setSessionId("");
            employee.setIsOnline(false);

            if (generalController.update(employee) == BusinessConstant.UPDATE.FAILURE) {
                throw new Exception();
            }

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            
            LanguageControllerManager.getInstance().removeSession(externalContext.getSessionId(true));
            
            externalContext.invalidateSession();
            navigation.redirect(WebConstant.WEB.LOGIN);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getCurrentEmployee() {
        System.out.println("# " + new Date() + " | " + WebConstant.LOGGER.SERVICE.AUTHENTICATION.GET_CURRENT_EMPLOYEE);

        String session = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
        return specificController.findBySession(session);
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public boolean hasVisibility(String zone) {
        boolean visibility = false;
        Employee current_employee = getCurrentEmployee();
        
        if (current_employee.getEmployeeType().getId().equals((long) 111)) {
            visibility = true;
        } else {
            if (zone.equals("relation")) {
                visibility = true;
            } else if (current_employee.getEmployeeType().getId().equals((long) 110) && zone.equals("entity")) {
                visibility = true;
            }
        }
        
        
        return visibility;
    }

}
