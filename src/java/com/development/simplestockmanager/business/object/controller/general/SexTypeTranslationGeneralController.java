package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeTranslationNull;
import com.development.simplestockmanager.business.object.helper.SexTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.SexTypeTranslationJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for SexTypeTranslation object
 *
 * @author foxtrot
 */
public class SexTypeTranslationGeneralController {

    private final SexTypeTranslationJpaController controller;

    public SexTypeTranslationGeneralController() {
        SexTypeTranslationHelper helper = new SexTypeTranslationHelper();
        controller = helper.getJpaController();
    }

    public long create(SexTypeTranslation employeeTypeTranslation) {
        try {
            controller.create(employeeTypeTranslation);
        } catch (Exception e) {
            employeeTypeTranslation = new SexTypeTranslationNull();
        }

        return employeeTypeTranslation.getId();
    }

    public SexTypeTranslation read(SexTypeTranslation employeeTypeTranslation) {
        try {
            employeeTypeTranslation = controller.findSexTypeTranslation(employeeTypeTranslation.getId());

            if (employeeTypeTranslation == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeTypeTranslation = new SexTypeTranslationNull();
        }

        return employeeTypeTranslation;
    }

    public long update(SexTypeTranslation employeeTypeTranslation) {
        long status;

        try {
            controller.edit(employeeTypeTranslation);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(SexTypeTranslation employeeTypeTranslation) {
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
