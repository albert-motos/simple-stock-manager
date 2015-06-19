/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.old.AnalyticsTimeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class AnalyticsTimeHelper {

    public static AnalyticsTimeJpaController getJpaController() {
        return new AnalyticsTimeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByAllQuery(int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("AnalyticsTime.findByAll");
        query.setParameter("minute", minute);
        query.setParameter("hour", hour);
        query.setParameter("day", day);
        query.setParameter("dayTypeID", dayTypeID);
        query.setParameter("month", month);
        query.setParameter("monthTypeID", monthTypeID);
        query.setParameter("year", year);
        
        return query;
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("AnalyticsTime.findById");
        query.setParameter("id", id);

        return query;
    }
}
