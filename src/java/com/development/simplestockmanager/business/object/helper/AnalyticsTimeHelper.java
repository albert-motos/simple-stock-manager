/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.AnalyticsTimeJpaController;

/**
 * Helper class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class AnalyticsTimeHelper {

    public AnalyticsTimeJpaController getJpaController() {
        return new AnalyticsTimeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }
}
