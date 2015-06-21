/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.object.helper.SexTypeHelper;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class SexTypeGeneralController {

    private final SexTypeHelper helper;
    private final SexTypeJpaController controller;

    public SexTypeGeneralController() {
        helper = new SexTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(SexType sexType) {
        Query query;

        if (sexType.getReferencedType() == null) {
            query = helper.getFindByRefencedType(sexType.getType());
        } else {
            query = helper.getFindByRefencedTypeAndLanguage(sexType.getReferencedType().getType(), sexType.getLanguageType().getCode());
        }
        
        if (query.getResultList().isEmpty()) {
            controller.create(sexType);
        } else {
            sexType = new SexTypeNull();
        }

        return sexType.getId();
    }

    public SexType read(SexType sexType) {
        try {
            Query query = helper.getFindByIdQuery(sexType.getId());
            sexType = (SexType) query.getSingleResult();
        } catch (Exception e) {
            sexType = new SexTypeNull();
        }

        return sexType;
    }

//    public long update(SexType sexType) {
////        try {
////            Query query = SexTypeHelper.getFindByIdQuery(sexType.getId());
////            sexType = (SexType) query.getSingleResult();
////        } catch (Exception e) {
////            sexType = new SexTypeNull();
////        }
////
////        return sexType;
//    }

//    public static long update(long id, String type) {
//
//        long status = Constant.UPDATE.FAILURE;
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
//            Query query = SexTypeHelper.getFindByTypeQuery(type);
//
//            if (query.getResultList().isEmpty()) {
//                SexType sexType = new SexType(id, type);
//
//                try {
//                    SexTypeJpaController controller = SexTypeHelper.getJpaController();
//                    controller.edit(sexType);
//                    status = Constant.UPDATE.SUCCESS;
//                } catch (Exception e) {
//
//                }
//            }
//        }
//
//        return status;
//    }
//
//    public static long delete(long id) {
//
//        long status = Constant.DELETE.FAILURE;
//
//        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
//            try {
//                SexTypeJpaController controller = SexTypeHelper.getJpaController();
//                controller.destroy(id);
//                status = Constant.DELETE.SUCCESS;
//            } catch (Exception e) {
//
//            }
//        }
//
//        return status;
//    }
}
