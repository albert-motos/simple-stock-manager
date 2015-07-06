package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.ProductNull;
import com.development.simplestockmanager.business.object.helper.ProductHelper;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.controller.ProductJpaController;

/**
 * General controller class for Product object
 *
 * @author foxtrot
 */
public class ProductGeneralController {

    private final ProductJpaController controller;

    public ProductGeneralController() {
        ProductHelper helper = new ProductHelper();
        controller = helper.getJpaController();
    }

    public long create(Product product) {
        try {
            controller.create(product);
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product.getId();
    }

    public Product read(Product product) {
        try {
            product = controller.findProduct(product.getId());

            if (product == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product;
    }

    public long update(Product product) {
        long status;

        try {
            controller.edit(product);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Product product) {
        long status;

        try {
            controller.destroy(product.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
