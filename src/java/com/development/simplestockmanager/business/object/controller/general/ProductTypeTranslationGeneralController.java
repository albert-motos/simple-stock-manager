package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeTranslationNull;
import com.development.simplestockmanager.business.object.helper.ProductTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.ProductTypeTranslationJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for ProductTypeTranslation object
 *
 * @author foxtrot
 */
public class ProductTypeTranslationGeneralController {

    private final ProductTypeTranslationJpaController controller;

    public ProductTypeTranslationGeneralController() {
        ProductTypeTranslationHelper helper = new ProductTypeTranslationHelper();
        controller = helper.getJpaController();
    }

    public long create(ProductTypeTranslation employeeTypeTranslation) {
        try {
            controller.create(employeeTypeTranslation);
        } catch (Exception e) {
            employeeTypeTranslation = new ProductTypeTranslationNull();
        }

        return employeeTypeTranslation.getId();
    }

    public ProductTypeTranslation read(ProductTypeTranslation employeeTypeTranslation) {
        try {
            employeeTypeTranslation = controller.findProductTypeTranslation(employeeTypeTranslation.getId());

            if (employeeTypeTranslation == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeTypeTranslation = new ProductTypeTranslationNull();
        }

        return employeeTypeTranslation;
    }

    public long update(ProductTypeTranslation employeeTypeTranslation) {
        long status;

        try {
            controller.edit(employeeTypeTranslation);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(ProductTypeTranslation employeeTypeTranslation) {
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
