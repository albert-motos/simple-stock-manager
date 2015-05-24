/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.StockNull;
import com.simplestockmanager.helper.StockHelper;
import com.simplestockmanager.persistence.Stock;
import com.simplestockmanager.persistence.controller.StockJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
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
    
    public static long update(long id, long productID, long brandID, long storeID, long providerID, BigDecimal actualAmount, BigDecimal totalAmount, Date createdDate,
            Date lastModifiedDate) {
        long status = UpdateConstant.FAILURE;
        
        if (read(id).getId() != IdentifierConstant.INVALID) {
            Stock stock = new Stock(id, productID, brandID, storeID, providerID, actualAmount, totalAmount, createdDate, lastModifiedDate);
            
            try {
                StockJpaController stockJpaController = StockHelper.getJpaController();
                stockJpaController.edit(stock);
                status = UpdateConstant.SUCCESS;
            } catch (Exception e) {
            }
        }
        
        return status;
    }
    
    public static long delete(long id) {
        long status = DeleteConstant.FAILURE;
        
        if (read(id).getId() != IdentifierConstant.INVALID) {
            try {
                StockJpaController stockJpaController = StockHelper.getJpaController();
                stockJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {
            }
        }
        
        return status;
    }
}
