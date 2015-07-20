package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.StockNull;
import com.development.simplestockmanager.business.object.helper.StockHelper;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.StockJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Stock object
 *
 * @author foxtrot
 */
public class StockGeneralController {

    private final StockJpaController controller;

    public StockGeneralController() {
        StockHelper helper = new StockHelper();
        controller = helper.getJpaController();
    }

    public long create(Stock stock) {
        try {
            controller.create(stock);
        } catch (Exception e) {
            stock = new StockNull();
        }

        return stock.getId();
    }

    public Stock read(Stock stock) {
        try {
            stock = controller.findStock(stock.getId());

            if (stock == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            stock = new StockNull();
        }

        return stock;
    }

    public long update(Stock stock) {
        long status;

        try {
            controller.edit(stock);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Stock stock) {
        long status;

        try {
            controller.destroy(stock.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
