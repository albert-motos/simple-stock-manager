package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.AnalyticsTimeNull;
import com.development.simplestockmanager.business.object.helper.AnalyticsTimeHelper;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.controller.AnalyticsTimeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class AnalyticsTimeGeneralController {

    private final AnalyticsTimeJpaController controller;

    public AnalyticsTimeGeneralController() {
        AnalyticsTimeHelper helper = new AnalyticsTimeHelper();
        controller = helper.getJpaController();
    }

    public long create(AnalyticsTime analyticsTime) {
        try {
            controller.create(analyticsTime);
        } catch (Exception e) {
            analyticsTime = new AnalyticsTimeNull();
        }

        return analyticsTime.getId();
    }

    public AnalyticsTime read(AnalyticsTime analyticsTime) {
        try {
            analyticsTime = controller.findAnalyticsTime(analyticsTime.getId());

            if (analyticsTime == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            analyticsTime = new AnalyticsTimeNull();
        }

        return analyticsTime;
    }

//    
    public long update(AnalyticsTime analyticsTime) {
        long status;

        try {
            controller.edit(analyticsTime);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(AnalyticsTime analyticsTime) {
        long status;

        try {
            controller.destroy(analyticsTime.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
