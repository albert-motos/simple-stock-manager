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

    public static DayType read(Long id) {
        DayType dayType;

        try {
            Query query = DayTypeHelper.getFindByIdQuery(id);
            dayType = (DayType) query.getSingleResult();
        } catch (Exception e) {
            dayType = new DayTypeNull();
        }

        return dayType;
    }

    public static long update(Long id, String type) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            DayType dayType = new DayType(id, type);

            try {
                DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                dayTypeJpaController.edit(dayType);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(Long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                DayTypeJpaController dayTypeJpaController = DayTypeHelper.getJpaController();
                dayTypeJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
