/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.DayTypeNull;
import com.development.simplestockmanager.business.object.helper.DayTypeHelper;
import com.development.simplestockmanager.business.persistence.DayType;
import com.development.simplestockmanager.business.persistence.controller.DayTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class DayTypeGeneralController {

    public static long create(String type) {

        DayType dayType;
        Query query = DayTypeHelper.getFindByTypeQuery(type);

        try {
            dayType = (DayType) query.getSingleResult();
        } catch (Exception e) {
            dayType = new DayType(type);

            try {
                DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                dayTypeJpaController.create(dayType);
            } catch (Exception e2) {
                dayType = new DayTypeNull();
            }
        }

        return dayType.getId();
    }

    public static DayType read(long id) {

        DayType dayType;

        try {
            Query query = DayTypeHelper.getFindByIdQuery(id);
            dayType = (DayType) query.getSingleResult();
        } catch (Exception e) {
            dayType = new DayTypeNull();
        }

        return dayType;
    }

    public static long update(long id, String type) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = DayTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                DayType dayType = new DayType(id, type);

                try {
                    DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                    dayTypeJpaController.edit(dayType);
                    status = Constant.UPDATE.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {

            try {
                DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                dayTypeJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
