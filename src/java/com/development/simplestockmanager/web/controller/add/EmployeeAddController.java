package com.development.simplestockmanager.web.controller.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.EmployeeCommonController;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Client object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeAdd")
@ViewScoped
public class EmployeeAddController extends EmployeeCommonController implements AddController {

    public EmployeeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        employee = new Employee();
//        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage());
    }

    @Override
    public void add() {
        employee.setSexType(sexTypeSelector.getSelectedValue());
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue());
        validator.setObject(employee);
        
        if (validator.validate()) {
            employee.setCreatedDate(new Date());
            employee.setLastModifiedDate(new Date());
            employee.setCreatedUser(user);
            employee.setLastModifiedUser(user);

            Long id = generalController.create(employee);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.CLIENT) + id +
                        languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
