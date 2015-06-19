/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.nullpackage.AnalyticsTimeNull;
import com.development.simplestockmanager.business.object.helper.AnalyticsTimeHelper;
import com.development.simplestockmanager.business.persistence.old.AnalyticsTime;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class AnalyticsTimeSpecificController {

    static public AnalyticsTime find(int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
        AnalyticsTime analyticsTime;

        try {
            Query query = AnalyticsTimeHelper.getFindByAllQuery(minute, hour, day, dayTypeID, month, monthTypeID, year);
            analyticsTime = (AnalyticsTime) query.getSingleResult();
        } catch (Exception e) {
            analyticsTime = new AnalyticsTimeNull();
        }
        
        return analyticsTime;
    }
}
