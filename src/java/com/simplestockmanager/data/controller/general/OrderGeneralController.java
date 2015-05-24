/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import static com.simplestockmanager.data.controller.general.ItemGeneralController.read;
import com.simplestockmanager.data.nullpackage.OrderNull;
import com.simplestockmanager.helper.ItemHelper;
import com.simplestockmanager.helper.OrderHelper;
import com.simplestockmanager.persistence.Item;
import com.simplestockmanager.persistence.Order;
import com.simplestockmanager.persistence.controller.ItemJpaController;
import com.simplestockmanager.persistence.controller.OrderJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class OrderGeneralController {

    public static Long create(long clientID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, long analitycsTimeID) {
        Order order = new Order(clientID, paymentTypeID, cost, createdDate, lastModifiedDate, analitycsTimeID);

        try {
            OrderJpaController orderJpaController = OrderHelper.getJpaController();
            orderJpaController.create(order);
        } catch (Exception e) {
            order = new OrderNull();
        }

        return order.getId();
    }

    public static Order read(Long id) {
        Order order;

        try {
            Query query = OrderHelper.getFindByIdQuery(id);
            order = (Order) query.getSingleResult();
        } catch (Exception e) {
            order = new OrderNull();
        }

        return order;
    }
    
    public static long update(Long id, long clientID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, long analitycsTimeID) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Order order = new Order(id, clientID, paymentTypeID, cost, createdDate, lastModifiedDate, analitycsTimeID);
            
            try {
                OrderJpaController orderJpaController = OrderHelper.getJpaController();
                orderJpaController.edit(order);
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
                OrderJpaController orderJpaController = OrderHelper.getJpaController();
                orderJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
