/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.ProductNull;
import com.simplestockmanager.helper.ProductHelper;
import com.simplestockmanager.persistence.Product;
import com.simplestockmanager.persistence.controller.ProductJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ProductGeneralController {

    public static long create(String name, String description, boolean isEnable, Date createdDate, Date lastModifiedDate) {

        Product product = new Product(name, description, isEnable, createdDate, lastModifiedDate);

        try {
            ProductJpaController productJpaController = ProductHelper.getJpaController();
            productJpaController.create(product);
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product.getId();
    }

    public static Product read(long id) {

        Product product;

        try {
            Query query = ProductHelper.getFindByIdQuery(id);
            product = (Product) query.getSingleResult();
        } catch (Exception e) {
            product = new ProductNull();
        }

        return product;
    }

    public static long update(long id, String name, String description, boolean isEnable, Date createdDate, Date lastModifiedDate) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Product product = new Product(id, name, description, isEnable, createdDate, lastModifiedDate);

            try {
                ProductJpaController productJpaController = ProductHelper.getJpaController();
                productJpaController.edit(product);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProductJpaController productJpaController = ProductHelper.getJpaController();
                productJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
