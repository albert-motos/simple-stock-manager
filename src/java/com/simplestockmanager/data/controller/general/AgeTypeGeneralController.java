/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.AgeTypeNull;
import com.simplestockmanager.helper.AgeTypeHelper;
import com.simplestockmanager.persistence.AgeType;
import com.simplestockmanager.persistence.controller.AgeTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class AgeTypeGeneralController {

    public static long create(String type) {
        AgeType ageType;

        Query query = AgeTypeHelper.getFindByTypeQuery(type);

        try {
            ageType = (AgeType) query.getSingleResult();
        } catch (Exception e) {
            ageType = new AgeType(type);

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

    public static long update(long id, String type) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Query query = AgeTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                AgeType ageType = new AgeType(id, type);

                try {
                    AgeTypeJpaController ageTypeJpaController = AgeTypeHelper.getJpaController();
                    ageTypeJpaController.edit(ageType);
                    state = UpdateConstant.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return state;
    }

    public static long delete(long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                AgeTypeJpaController ageTypeJpaController = AgeTypeHelper.getJpaController();
                ageTypeJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}
