package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.PriceNull;
import com.development.simplestockmanager.business.object.helper.PriceHelper;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Price object
 *
 * @author foxtrot
 */
public class PriceGeneralController {

    private final PriceJpaController controller;

    public PriceGeneralController() {
        PriceHelper helper = new PriceHelper();
        controller = helper.getJpaController();
    }

    public long create(Price price) {
        try {
            controller.create(price);
        } catch (Exception e) {
            price = new PriceNull();
        }

        return price.getId();
    }

    public Price read(Price price) {
        try {
            price = controller.findPrice(price.getId());

            if (price == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            price = new PriceNull();
        }

        return price;
    }

    public long update(Price price) {
        long status;

        try {
            controller.edit(price);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Price price) {
        long status;

        try {
            controller.destroy(price.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
