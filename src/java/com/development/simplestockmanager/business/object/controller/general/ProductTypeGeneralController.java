package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.helper.ProductTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeNull;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.controller.ProductTypeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeGeneralController {

    private final ProductTypeJpaController controller;

    public ProductTypeGeneralController() {
        ProductTypeHelper helper = new ProductTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(ProductType productType) {
        try {
            controller.create(productType);
        } catch (Exception e) {
            productType = new ProductTypeNull();
        }

        return productType.getId();
    }

    public ProductType read(ProductType productType) {
        try {
            productType = controller.findProductType(productType.getId());

            if (productType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            productType = new ProductTypeNull();
        }

        return productType;
    }

    public long update(ProductType productType) {
        long status;

        try {
            controller.edit(productType);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(ProductType productType) {
        long status;

        try {
            controller.destroy(productType.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
