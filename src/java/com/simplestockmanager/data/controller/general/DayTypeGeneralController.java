/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.DayTypeNull;
import com.simplestockmanager.helper.DayTypeHelper;
import com.simplestockmanager.persistence.DayType;
import com.simplestockmanager.persistence.controller.DayTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
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

        long status = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Query query = DayTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                DayType dayType = new DayType(id, type);

                try {
                    DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                    dayTypeJpaController.edit(dayType);
                    status = UpdateConstant.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                dayTypeJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
