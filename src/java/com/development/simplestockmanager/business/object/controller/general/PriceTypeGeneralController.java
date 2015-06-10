/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.object.helper.PriceTypeHelper;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.controller.PriceTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class PriceTypeGeneralController {

    public static long create(String type) {

        PriceType priceType;
        Query query = PriceTypeHelper.getFindByTypeQuery(type);

        try {
            priceType = (PriceType) query.getSingleResult();
        } catch (Exception e) {
            priceType = new PriceType(type);

            try {
                PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                priceTypeJpaController.create(priceType);
            } catch (Exception e2) {
                priceType = new PriceTypeNull();
            }
        }

        return priceType.getId();
    }

    public static PriceType read(long id) {

        PriceType priceType;

        try {
            Query query = PriceTypeHelper.getFindByIdQuery(id);
            priceType = (PriceType) query.getSingleResult();
        } catch (Exception e) {
            priceType = new PriceTypeNull();
        }

        return priceType;
    }

    public static long update(long id, String type) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = PriceTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                PriceType priceType = new PriceType(id, type);

                try {
                    PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                    priceTypeJpaController.edit(priceType);
                    status = Constant.UPDATE.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                priceTypeJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
