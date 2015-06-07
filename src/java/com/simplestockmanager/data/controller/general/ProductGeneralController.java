package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.ProductNull;
import com.simplestockmanager.helper.ProductHelper;
import com.simplestockmanager.persistence.Product;
import com.simplestockmanager.persistence.controller.ProductJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ProductGeneralController {

    public long create(Product product) {
        try {
            ProductJpaController productJpaController = ProductHelper.getJpaController();
            productJpaController.create(product);
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product.getId();
    }

    public Product read(Product product) {
        try {
            Query query = ProductHelper.getFindByIdQuery(product.getId());
            product = (Product) query.getSingleResult();
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product;
    }

    public long update(Product product) {
        long status = Constant.UPDATE.FAILURE;

        if (read(product).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProductJpaController productJpaController = ProductHelper.getJpaController();
                productJpaController.edit(product);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public long delete(Product product) {
        long status = Constant.DELETE.FAILURE;

        if (read(product).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProductJpaController productJpaController = ProductHelper.getJpaController();
                productJpaController.destroy(product.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
