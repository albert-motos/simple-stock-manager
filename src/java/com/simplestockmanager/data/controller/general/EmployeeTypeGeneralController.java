/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.EmployeeTypeNull;
import com.simplestockmanager.helper.EmployeeTypeHelper;
import com.simplestockmanager.persistence.EmployeeType;
import com.simplestockmanager.persistence.controller.EmployeeTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 * @author foxtrot
 */
public class EmployeeTypeGeneralController {

    public static long create(String type) {
        EmployeeType employeeType;

        Query query = EmployeeTypeHelper.getFindByTypeQuery(type);

        try {
            employeeType = (EmployeeType) query.getSingleResult();
        } catch (Exception e) {
            employeeType = new EmployeeType(type);

            try {
                EmployeeTypeJpaController employeeTypeJpaController = EmployeeTypeHelper.getJpaController();
                employeeTypeJpaController.create(employeeType);
            } catch (Exception e2) {
                employeeType = new EmployeeTypeNull();
            }
        }

        return employeeType.getId();
    }

    public static EmployeeType read(long id) {
        EmployeeType employeeType;

        try {
            Query query = EmployeeTypeHelper.getFindByIdQuery(id);
            employeeType = (EmployeeType) query.getSingleResult();
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType;
    }

    public static long update(long id, String type) {

        long status = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Query query = EmployeeTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                EmployeeType employeeType = new EmployeeType(id, type);

                try {
                    EmployeeTypeJpaController employeeTypeJpaController = EmployeeTypeHelper.getJpaController();
                    employeeTypeJpaController.edit(employeeType);
                    status = UpdateConstant.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            try {
                EmployeeTypeJpaController employeeTypeJpaController = EmployeeTypeHelper.getJpaController();
                employeeTypeJpaController.destroy(id);
                status = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
