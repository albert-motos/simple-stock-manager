/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.AnalyticsTimeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.AnalyticsTimeNull;
import com.development.simplestockmanager.business.object.helper.AnalyticsTimeHelper;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.controller.AnalyticsTimeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 * @author foxtrot
 */
public class AnalyticsTimeGeneralController {

//    public static long create(int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
//        
//        AnalyticsTime analyticsTime = AnalyticsTimeSpecificController.find(minute, hour, day, dayTypeID, month, monthTypeID, year);
//
//        if (analyticsTime.getId() == Constant.IDENTIFIER.INVALID) {
//            analyticsTime = new AnalyticsTime(minute, hour, day, dayTypeID, month, monthTypeID, year);
//
//            try {
//                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
//                analyticsTimeJpaController.create(analyticsTime);
//            } catch (Exception e) {
//                analyticsTime = new AnalyticsTimeNull();
//            }
//        }
//
//        return analyticsTime.getId();
//    }
//
//    public static AnalyticsTime read(long id) {
//        
//        AnalyticsTime analyticsTime;
//
//        try {
//            Query query = AnalyticsTimeHelper.getFindByIdQuery(id);
//            analyticsTime = (AnalyticsTime) query.getSingleResult();
//        } catch (Exception e) {
//            analyticsTime = new AnalyticsTimeNull();
//        }
//
//        return analyticsTime;
//    }
//
//    public static long update(long id, int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
//
//        long status = Constant.UPDATE.FAILURE;
//        
//        AnalyticsTime analyticsTime = AnalyticsTimeSpecificController.find(minute, hour, day, dayTypeID, month, monthTypeID, year);
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID && analyticsTime.getId() == Constant.IDENTIFIER.INVALID) {
//            analyticsTime = new AnalyticsTime(id, minute, hour, day, dayTypeID, month, monthTypeID, year);
//
//            try {
//                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
//                analyticsTimeJpaController.edit(analyticsTime);
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
//        long status = Constant.DELETE.FAILURE;
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
//            try {
//                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
//                analyticsTimeJpaController.destroy(id);
//                status = Constant.DELETE.SUCCESS;
//            } catch (Exception e) {
//
//            }
//        }
//
//        return status;
//    }
}
