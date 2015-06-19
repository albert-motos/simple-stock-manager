/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.StockNull;
import com.development.simplestockmanager.business.object.helper.StockHelper;
import com.development.simplestockmanager.business.persistence.old.Stock;
import com.development.simplestockmanager.business.persistence.controller.old.StockJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class StockGeneralController {

    public static long create(long productID, long brandID, long storeID, long providerID, BigDecimal actualAmount, BigDecimal totalAmount, Date createdDate,
            Date lastModifiedDate) {

        Stock stock = new Stock(productID, brandID, storeID, providerID, actualAmount, totalAmount, createdDate, lastModifiedDate);

        try {
            StockJpaController stockJpaController = StockHelper.getJpaController();
            stockJpaController.create(stock);
        } catch (Exception e) {
            stock = new StockNull();
        }

        return stock.getId();
    }

    public static Stock read(long id) {

        Stock stock;

        try {
            Query query = StockHelper.getFindByIdQuery(id);
            stock = (Stock) query.getSingleResult();
        } catch (Exception e) {
            stock = new StockNull();
        }

        return stock;
    }

    public static long update(long id, long productID, long brandID, long storeID, long providerID, BigDecimal actualAmount, BigDecimal totalAmount,
            Date createdDate, Date lastModifiedDate) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Stock stock = new Stock(id, productID, brandID, storeID, providerID, actualAmount, totalAmount, createdDate, lastModifiedDate);

            try {
                StockJpaController stockJpaController = StockHelper.getJpaController();
                stockJpaController.edit(stock);
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
                StockJpaController stockJpaController = StockHelper.getJpaController();
                stockJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
