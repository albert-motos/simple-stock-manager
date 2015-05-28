/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.EmployeeNull;
import com.simplestockmanager.helper.EmployeeHelper;
import com.simplestockmanager.persistence.Employee;
import com.simplestockmanager.persistence.controller.EmployeeJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class EmployeeGeneralController {

    public static Long create(String firstName, String lastName, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate,
            Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {

        Query query = EmployeeHelper.getFindByUserNameQuery(userName);
        Employee employee = new EmployeeNull();

        if (query.getResultList().isEmpty()) {
            employee = new Employee(firstName, lastName, phone, email, employeTypeID, isEnable, createdDate, lastModifiedDate, userName, password, isOnline,
                    lastOnlineDate, sessionID);

            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.create(employee);
            } catch (Exception e) {
                employee = new EmployeeNull();
            }
        }

        return employee.getId();
    }

    public static Employee read(long id) {

        Employee employee;

        try {
            Query query = EmployeeHelper.getFindByIdQuery(id);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee;
    }

    public static long update(long id, String firstName, String lastName, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate,
            Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = EmployeeHelper.getFindByUserNameQuery(userName);
            Employee employee;

            try {
                employee = (Employee) query.getSingleResult();
                if (employee.getId() == id) {
                    throw new Exception();
                }
            } catch (Exception e) {
                employee = new Employee(id, firstName, lastName, phone, email, employeTypeID, isEnable, createdDate, lastModifiedDate, userName,
                        password, isOnline, lastOnlineDate, sessionID);

                try {
                    EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                    employeeJpaController.edit(employee);
                    status = Constant.UPDATE.SUCCESS;
                } catch (Exception e2) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {

            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
