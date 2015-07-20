package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeTranslationNull;
import com.development.simplestockmanager.business.object.helper.EmployeeTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeTranslationJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for EmployeeTypeTranslation object
 *
 * @author foxtrot
 */
public class EmployeeTypeTranslationGeneralController {

    private final EmployeeTypeTranslationJpaController controller;

    public EmployeeTypeTranslationGeneralController() {
        EmployeeTypeTranslationHelper helper = new EmployeeTypeTranslationHelper();
        controller = helper.getJpaController();
    }

    public long create(EmployeeTypeTranslation employeeTypeTranslation) {
        try {
            controller.create(employeeTypeTranslation);
        } catch (Exception e) {
            employeeTypeTranslation = new EmployeeTypeTranslationNull();
        }

        return employeeTypeTranslation.getId();
    }

    public EmployeeTypeTranslation read(EmployeeTypeTranslation employeeTypeTranslation) {
        try {
            employeeTypeTranslation = controller.findEmployeeTypeTranslation(employeeTypeTranslation.getId());

            if (employeeTypeTranslation == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeTypeTranslation = new EmployeeTypeTranslationNull();
        }

        return employeeTypeTranslation;
    }

    public long update(EmployeeTypeTranslation employeeTypeTranslation) {
        long status;

        try {
            controller.edit(employeeTypeTranslation);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(EmployeeTypeTranslation employeeTypeTranslation) {
        long status;

        try {
            controller.destroy(employeeTypeTranslation.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
