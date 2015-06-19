/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.MonthTypeNull;
import com.development.simplestockmanager.business.object.helper.MonthTypeHelper;
import com.development.simplestockmanager.business.persistence.old.MonthType;
import com.development.simplestockmanager.business.persistence.controller.MonthTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class MonthTypeGeneralController {

    public static long create(String type) {

        MonthType monthType;
        Query query = MonthTypeHelper.getFindByTypeQuery(type);

        try {
            monthType = (MonthType) query.getSingleResult();
        } catch (Exception e) {
            monthType = new MonthType(type);

            try {
                MonthTypeJpaController monthTypeJpaController = MonthTypeHelper.getJpaController();
                monthTypeJpaController.create(monthType);
            } catch (Exception e2) {
                monthType = new MonthTypeNull();
            }
        }

        return monthType.getId();
    }

    public static MonthType read(long id) {

        MonthType monthType;

        try {
            Query query = MonthTypeHelper.getFindByIdQuery(id);
            monthType = (MonthType) query.getSingleResult();
        } catch (Exception e) {
            monthType = new MonthTypeNull();
        }

        return monthType;
    }

    public static long update(long id, String type) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = MonthTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                MonthType monthType = new MonthType(id, type);

                try {
                    MonthTypeJpaController monthTypeJpaController = MonthTypeHelper.getJpaController();
                    monthTypeJpaController.edit(monthType);
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
                MonthTypeJpaController monthTypeJpaController = MonthTypeHelper.getJpaController();
                monthTypeJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
