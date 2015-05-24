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
 *
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

    public static EmployeeType read(Long id) {
        EmployeeType employeeType;

        try {
            Query query = EmployeeTypeHelper.getFindByIdQuery(id);
            employeeType = (EmployeeType) query.getSingleResult();
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType;
    }

    public static long update(Long id, String type) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            EmployeeType employeeType = new EmployeeType(id, type);

            try {
                EmployeeTypeJpaController employeeTypeJpaController = EmployeeTypeHelper.getJpaController();
                employeeTypeJpaController.edit(employeeType);
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
                EmployeeTypeJpaController employeeTypeJpaController = EmployeeTypeHelper.getJpaController();
                employeeTypeJpaController.destroy(id);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
