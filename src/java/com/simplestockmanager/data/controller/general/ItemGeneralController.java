/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.ItemNull;
import com.simplestockmanager.helper.ItemHelper;
import com.simplestockmanager.persistence.Item;
import com.simplestockmanager.persistence.controller.ItemJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ItemGeneralController {

    public static Long create(long orderID, long stockID, long priceID, BigDecimal amount, BigDecimal cost, Date createdDate, Date lastModifiedDate) {
        Item item = new Item(orderID, stockID, priceID, amount, cost, createdDate, lastModifiedDate);

        try {
            ItemJpaController itemJpaController = ItemHelper.getJpaController();
            itemJpaController.create(item);
        } catch (Exception e) {
            item = new ItemNull();
        }

        return item.getId();
    }
    
    public static Item read(Long id) {
        Item item;
        
        try {
            Query query = ItemHelper.getFindByIdQuery(id);
            item = (Item) query.getSingleResult();
        } catch (Exception e) {
            item = new ItemNull();
        }
        
        return item;
    }
    
    public static long update(Long id, long orderID, long stockID, long priceID, BigDecimal amount, BigDecimal cost, Date createdDate, Date lastModifiedDate) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Item item = new Item(id, orderID, stockID, priceID, amount, cost, createdDate, lastModifiedDate);
            
            try {
                ItemJpaController itemJpaController = ItemHelper.getJpaController();
                itemJpaController.edit(item);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(Long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                ItemJpaController itemJpaController = ItemHelper.getJpaController();
                itemJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
