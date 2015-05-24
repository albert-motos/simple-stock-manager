/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.EmployeeNull;
import com.simplestockmanager.helper.EmployeeHelper;
import com.simplestockmanager.persistence.Employee;
import com.simplestockmanager.persistence.controller.EmployeeJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeGeneralController {

    public static Long create(String firstName, String lastName, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate,
            Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {
        
        Employee employee = new Employee(firstName, lastName, phone, email, employeTypeID, isEnable, createdDate, lastModifiedDate, userName, password,
                isOnline, lastOnlineDate, sessionID);

        try {
            EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
            employeeJpaController.create(employee);
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee.getId();
    }

    public static Employee read(Long id) {
        Employee employee;

        try {
            Query query = EmployeeHelper.getFindByIdQuery(id);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            employee = new EmployeeNull();
        }

        return employee;
    }

    public static long update(Long id, String firstName, String lastName, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate,
            Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Employee employee = new Employee(id, firstName, lastName, phone, email, employeTypeID, isEnable, createdDate, lastModifiedDate, userName,
                    password, isOnline, lastOnlineDate, sessionID);

            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.edit(employee);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(Long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                EmployeeJpaController employeeJpaController = EmployeeHelper.getJpaController();
                employeeJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
