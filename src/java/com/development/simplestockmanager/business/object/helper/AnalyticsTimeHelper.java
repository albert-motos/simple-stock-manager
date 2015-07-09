package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.AnalyticsTimeJpaController;

/**
 * Helper class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class AnalyticsTimeHelper extends BaseHelper {

    public AnalyticsTimeJpaController getJpaController() {
        return new AnalyticsTimeJpaController(entityManagerFactory);
    }
}
