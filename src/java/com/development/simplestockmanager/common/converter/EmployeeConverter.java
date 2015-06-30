package com.development.simplestockmanager.common.converter;

import com.development.simplestockmanager.business.object.controller.general.EmployeeTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.SexTypeGeneralController;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.EmployeeType;

/**
 * Converter class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeConverter {

    private final SexTypeGeneralController sexTypeGeneralController;
    private final EmployeeTypeGeneralController employeeTypeGeneralController;

    public EmployeeConverter() {
        sexTypeGeneralController = new SexTypeGeneralController();
        employeeTypeGeneralController = new EmployeeTypeGeneralController();
    }

    public com.development.simplestockmanager.business.persistence.Employee getBusinessObject(com.development.simplestockmanager.web.object.Employee web) {
        com.development.simplestockmanager.business.persistence.Employee business = new Employee();
        business.setBornDate(web.getBornDate());
        business.setCreatedDate(web.getCreatedDate());
        business.setCreatedUser(web.getCreatedUser());
        business.setEmail(web.getEmail());
        business.setEmployeeType(employeeTypeGeneralController.read(new EmployeeType(web.getEmployeeType())));
        business.setEnable(web.isEnable());
        business.setFirstname(web.getFirstname());
        business.setId(web.getId());
        business.setIsOnline(web.isIsOnline());
        business.setLanguageType(web.getLanguageType());
        business.setLastModifiedDate(web.getLastModifiedDate());
        business.setLastModifiedUser(web.getLastModifiedUser());
        business.setLastOnlineDate(web.getLastOnlineDate());
        business.setLastname(web.getLastname());
        business.setPassword(web.getPassword());
        business.setPhone(web.getPhone());
        business.setSessionId(web.getSessionId());
        business.setSexType(sexTypeGeneralController.read(new SexType(web.getSexType())));
        business.setUsername(web.getUsername());

        return business;
    }
}
