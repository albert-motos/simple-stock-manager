/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.AgeTypeNull;
import com.development.simplestockmanager.business.object.helper.AgeTypeHelper;
import com.development.simplestockmanager.business.persistence.old.AgeType;
import com.development.simplestockmanager.business.persistence.controller.old.AgeTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class AgeTypeGeneralController {

    public static long create(String type, int fromAge, int toAge) {

        AgeType ageType;
        Query query = AgeTypeHelper.getFindByTypeQuery(type);

        try {
            ageType = (AgeType) query.getSingleResult();
        } catch (Exception e) {
            ageType = new AgeType(type, fromAge, toAge);

            try {
                AgeTypeJpaController ageTypeJpaController = AgeTypeHelper.getJpaController();
                ageTypeJpaController.create(ageType);
            } catch (Exception e2) {
                ageType = new AgeTypeNull();
            }
        }

        return ageType.getId();
    }

    public static AgeType read(long id) {

        AgeType ageType;

        try {
            Query query = AgeTypeHelper.getFindByIdQuery(id);
            ageType = (AgeType) query.getSingleResult();
        } catch (Exception e) {
            ageType = new AgeTypeNull();
        }

        return ageType;
    }

    public static long update(long id, String type, int fromAge, int toAge) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = AgeTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                AgeType ageType = new AgeType(id, type, fromAge, toAge);

                try {
                    AgeTypeJpaController ageTypeJpaController = AgeTypeHelper.getJpaController();
                    ageTypeJpaController.edit(ageType);
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
                AgeTypeJpaController ageTypeJpaController = AgeTypeHelper.getJpaController();
                ageTypeJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
