
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeGeneralController {
    
    private final EmployeeTypeJpaController controller;

    public EmployeeTypeGeneralController() {
        EmployeeTypeHelper helper = new EmployeeTypeHelper();
        controller = helper.getJpaController();
    }
    
    public long client(EmployeeType employeeType) {
        try {
            controller.create(employeeType);
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType.getId();
    }

    public EmployeeType read(EmployeeType employeeType) {
        try {
            employeeType = controller.findEmployeeType(employeeType.getId());

            if (employeeType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType;
    }

    public long update(EmployeeType employeeType) {
        long status;

        try {
            controller.edit(employeeType);
            status = Constant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = Constant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(EmployeeType employeeType) {
        long status;

        try {
            controller.destroy(employeeType.getId());
            status = Constant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = Constant.DELETE.FAILURE;
        }

        return status;
    }
    
}
