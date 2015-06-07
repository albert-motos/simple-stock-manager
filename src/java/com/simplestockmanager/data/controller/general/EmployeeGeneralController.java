package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.EmployeeNull;
import com.simplestockmanager.helper.EmployeeHelper;
import com.simplestockmanager.persistence.Employee;
import com.simplestockmanager.persistence.controller.EmployeeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class EmployeeGeneralController {

    public long create(Employee employee) {
        Query query = EmployeeHelper.getFindByUserNameQuery(employee.getUserName());

        if (query.getResultList().isEmpty()) {
            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.create(employee);
            } catch (Exception e) {
                employee = new EmployeeNull();
            }
        } else {
            employee = new EmployeeNull();
        }

        return employee.getId();
    }

    public Employee read(Employee employee) {
        try {
            Query query = EmployeeHelper.getFindByIdQuery(employee.getId());
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee;
    }

    public long update(Employee employee) {
        long status = Constant.UPDATE.FAILURE;

        if (read(employee).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = EmployeeHelper.getFindByUserNameQuery(employee.getUserName());
            boolean uniqueUserName = true;

            if (!query.getResultList().isEmpty()) {
                Employee otherEmployee = (Employee) query.getSingleResult();
                uniqueUserName = employee.getId().equals(otherEmployee.getId());
            }

            if (uniqueUserName) {
                try {
                    EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                    employeeJpaController.edit(employee);
                    status = Constant.UPDATE.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public long delete(Employee employee) {
        long status = Constant.DELETE.FAILURE;

        if (read(employee).getId() != Constant.IDENTIFIER.INVALID) {

            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.destroy(employee.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
