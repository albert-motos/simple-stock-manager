package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.object.helper.EmployeeHelper;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.controller.EmployeeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeGeneralController {

    private final EmployeeJpaController controller;

    public EmployeeGeneralController() {
        EmployeeHelper helper = new EmployeeHelper();
        controller = helper.getJpaController();
    }

    public long create(Employee employee) {
        try {
            controller.create(employee);
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee.getId();
    }

    public Employee read(Employee employee) {
        try {
            employee = controller.findEmployee(employee.getId());

            if (employee == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee;
    }

//    
    public long update(Employee employee) {
        long status;

        try {
            controller.edit(employee);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Employee employee) {
        long status;

        try {
            controller.destroy(employee.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
