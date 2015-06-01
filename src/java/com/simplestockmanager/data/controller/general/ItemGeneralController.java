/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.ItemNull;
import com.simplestockmanager.helper.ItemHelper;
import com.simplestockmanager.persistence.Item;
import com.simplestockmanager.persistence.controller.ItemJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ItemGeneralController {

    public static long create(long orderID, long stockID, long priceID, BigDecimal amount, BigDecimal cost, Date createdDate, Date lastModifiedDate) {

        Item item = new Item(orderID, stockID, priceID, amount, cost, createdDate, lastModifiedDate);

        try {
            ItemJpaController itemJpaController = ItemHelper.getJpaController();
            itemJpaController.create(item);
        } catch (Exception e) {
            item = new ItemNull();
        }

        return item.getId();
    }

    public static Item read(long id) {

        Item item;

        try {
            Query query = ItemHelper.getFindByIdQuery(id);
            item = (Item) query.getSingleResult();
        } catch (Exception e) {
            item = new ItemNull();
        }

        return item;
    }

    public static long update(long id, long orderID, long stockID, long priceID, BigDecimal amount, BigDecimal cost, Date createdDate, Date lastModifiedDate) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Item item = new Item(id, orderID, stockID, priceID, amount, cost, createdDate, lastModifiedDate);

            try {
                ItemJpaController itemJpaController = ItemHelper.getJpaController();
                itemJpaController.edit(item);
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
                ItemJpaController itemJpaController = ItemHelper.getJpaController();
                itemJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}