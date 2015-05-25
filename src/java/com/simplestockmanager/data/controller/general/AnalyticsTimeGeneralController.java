/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.controller.specific.AnalyticsTimeSpecificController;
import com.simplestockmanager.data.nullpackage.AnalyticsTimeNull;
import com.simplestockmanager.helper.AnalyticsTimeHelper;
import com.simplestockmanager.persistence.AnalyticsTime;
import com.simplestockmanager.persistence.controller.AnalyticsTimeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 * @author foxtrot
 */
public class AnalyticsTimeGeneralController {

    public static long create(int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
        AnalyticsTime analyticsTime = AnalyticsTimeSpecificController.find(minute, hour, day, dayTypeID, month, monthTypeID, year);

        if (analyticsTime.getId() == IdentifierConstant.INVALID) {
            analyticsTime = new AnalyticsTime(minute, hour, day, dayTypeID, month, monthTypeID, year);

            try {
                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
                analyticsTimeJpaController.create(analyticsTime);
            } catch (Exception e) {
                analyticsTime = new AnalyticsTimeNull();
            }
        }

        return analyticsTime.getId();
    }

    public static AnalyticsTime read(long id) {
        AnalyticsTime analyticsTime;

        try {
            Query query = AnalyticsTimeHelper.getFindByIdQuery(id);
            analyticsTime = (AnalyticsTime) query.getSingleResult();
        } catch (Exception e) {
            analyticsTime = new AnalyticsTimeNull();
        }

        return analyticsTime;
    }

    public static long update(long id, int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {

        long status = UpdateConstant.FAILURE;

        AnalyticsTime analyticsTime = AnalyticsTimeSpecificController.find(minute, hour, day, dayTypeID, month, monthTypeID, year);

        if (read(id).getId() != IdentifierConstant.INVALID && analyticsTime.getId() == IdentifierConstant.INVALID) {
            analyticsTime = new AnalyticsTime(id, minute, hour, day, dayTypeID, month, monthTypeID, year);

            try {
                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
                analyticsTimeJpaController.edit(analyticsTime);
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
                AnalyticsTimeJpaController analyticsTimeJpaController = AnalyticsTimeHelper.getJpaController();
                analyticsTimeJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
