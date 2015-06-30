/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.PriceNull;
import com.development.simplestockmanager.business.object.helper.PriceHelper;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.controller.PriceJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class PriceGeneralController {

//    public static long create(long stockID, String title, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, BigDecimal initialAmount,
//            BigDecimal actualAmount, Date endDate, boolean isEnable) {
//
//        Price price = new Price(stockID, title, priceTypeID, cost, createdDate, lastModifiedDate, initialAmount, actualAmount, endDate, isEnable);
//
//        try {
//            PriceJpaController priceJpaController = PriceHelper.getJpaController();
//            priceJpaController.create(price);
//        } catch (Exception e) {
//            price = new PriceNull();
//        }
//
//        return price.getId();
//    }
//
//    public static Price read(long id) {
//
//        Price price;
//
//        try {
//            Query query = PriceHelper.getFindByIdQuery(id);
//            price = (Price) query.getSingleResult();
//        } catch (Exception e) {
//            price = new PriceNull();
//        }
//
//        return price;
//    }
//
//    public static long update(long id, long stockID, String title, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate,
//            BigDecimal initialAmount, BigDecimal actualAmount, Date endDate, boolean isEnable) {
//
//        long status = Constant.UPDATE.FAILURE;
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
//            Price price = new Price(id, stockID, title, priceTypeID, cost, createdDate, lastModifiedDate, isEnable);
//
//            try {
//                PriceJpaController priceJpaController = PriceHelper.getJpaController();
//                priceJpaController.edit(price);
//                status = Constant.UPDATE.SUCCESS;
//            } catch (Exception e) {
//
//            }
//        }
//
//        return status;
//    }
//
//    public static long delete(long id) {
//
//        long state = Constant.DELETE.FAILURE;
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
//            try {
//                PriceJpaController priceJpaController = PriceHelper.getJpaController();
//                priceJpaController.destroy(id);
//                state = Constant.DELETE.SUCCESS;
//            } catch (Exception e) {
//
//            }
//        }
//
//        return state;
//    }
}
