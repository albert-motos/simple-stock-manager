package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeTranslationNull;
import com.development.simplestockmanager.business.object.helper.PriceTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.PriceTypeTranslationJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for PriceTypeTranslation object
 *
 * @author foxtrot
 */
public class PriceTypeTranslationGeneralController {

    private final PriceTypeTranslationJpaController controller;

    public PriceTypeTranslationGeneralController() {
        PriceTypeTranslationHelper helper = new PriceTypeTranslationHelper();
        controller = helper.getJpaController();
    }

    public long create(PriceTypeTranslation employeeTypeTranslation) {
        try {
            controller.create(employeeTypeTranslation);
        } catch (Exception e) {
            employeeTypeTranslation = new PriceTypeTranslationNull();
        }

        return employeeTypeTranslation.getId();
    }

    public PriceTypeTranslation read(PriceTypeTranslation employeeTypeTranslation) {
        try {
            employeeTypeTranslation = controller.findPriceTypeTranslation(employeeTypeTranslation.getId());

            if (employeeTypeTranslation == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeTypeTranslation = new PriceTypeTranslationNull();
        }

        return employeeTypeTranslation;
    }

    public long update(PriceTypeTranslation employeeTypeTranslation) {
        long status;

        try {
            controller.edit(employeeTypeTranslation);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(PriceTypeTranslation employeeTypeTranslation) {
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
