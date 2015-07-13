package com.development.simplestockmanager.web.controller.edit;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.EditController;
import com.development.simplestockmanager.web.controller.common.EmployeeCommonController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Employee object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeEdit")
@ViewScoped
public class EmployeeEditController extends EmployeeCommonController implements EditController {

    private Employee baseEmployee;
    
    public EmployeeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
//        try {
//            employee = (Employee) receiveObjectFromSession(WebConstant.SESSION.EMPLOYEE);
//            baseEmployee = new Client(client);
//        } catch (Exception e) {
//            back();
//        }
//        
//        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, client.getSexType(), languageController.getLanguage());
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
