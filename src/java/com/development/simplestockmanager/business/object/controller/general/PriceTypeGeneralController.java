package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.object.helper.PriceTypeHelper;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.controller.PriceTypeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeGeneralController {

    private final PriceTypeJpaController controller;

    public PriceTypeGeneralController() {
        PriceTypeHelper helper = new PriceTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(PriceType priceType) {
        try {
            controller.create(priceType);
        } catch (Exception e) {
            priceType = new PriceTypeNull();
        }

        return priceType.getId();
    }

    public PriceType read(PriceType priceType) {
        try {
            priceType = controller.findPriceType(priceType.getId());

            if (priceType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            priceType = new PriceTypeNull();
        }

        return priceType;
    }

    public long update(PriceType priceType) {
        long status;

        try {
            controller.edit(priceType);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(PriceType priceType) {
        long status;

        try {
            controller.destroy(priceType.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException | IllegalOrphanException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
