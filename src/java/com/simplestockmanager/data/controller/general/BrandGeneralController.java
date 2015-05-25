/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.BrandNull;
import com.simplestockmanager.helper.BrandHelper;
import com.simplestockmanager.persistence.Brand;
import com.simplestockmanager.persistence.controller.BrandJpaController;
import javax.persistence.Query;

/**
 * TESTED
 * @author foxtrot
 */
public class BrandGeneralController {

    public static long create(String name, boolean enable) {
        Brand brand;

        Query query = BrandHelper.getFindByNameQuery(name);

        try {
            brand = (Brand) query.getSingleResult();
        } catch (Exception e) {
            brand = new Brand(name, enable);

            try {
                BrandJpaController brandJpaController = BrandHelper.getJpaController();
                brandJpaController.create(brand);
            } catch (Exception e2) {
                brand = new BrandNull();
            }
        }

        return brand.getId();
    }

    public static Brand read(long id) {
        Brand brand;

        try {
            Query query = BrandHelper.getFindByIdQuery(id);
            brand = (Brand) query.getSingleResult();
        } catch (Exception e) {
            brand = new BrandNull();
        }

        return brand;
    }

    public static long update(long id, String name, boolean enable) {

        long status = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            Query query = BrandHelper.getFindByNameQuery(name);
            Brand brand;
            
            try {
                brand = (Brand) query.getSingleResult();
                if (brand.getId() == id) {
                    throw new Exception();
                }
            } catch (Exception e) {
                brand = new Brand(id, name, enable);

                try {
                    BrandJpaController brandJpaController = BrandHelper.getJpaController();
                    brandJpaController.edit(brand);
                    status = UpdateConstant.SUCCESS;
                } catch (Exception e2) {
                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                BrandJpaController brandJpaController = BrandHelper.getJpaController();
                brandJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
