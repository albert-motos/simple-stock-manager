package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.helper.SexTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.controller.SexTypeJpaController;

/**
 * General controller class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeGeneralController {

    private final SexTypeJpaController controller;

    public SexTypeGeneralController() {
        SexTypeHelper helper = new SexTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(SexType sexType) {
        try {
            controller.create(sexType);
        } catch (Exception e) {
            sexType = new SexTypeNull();
        }

        return sexType.getId();
    }

    public SexType read(SexType sexType) {
        try {
            sexType = controller.findSexType(sexType.getId());

            if (sexType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            sexType = new SexTypeNull();
        }

        return sexType;
    }

    public long update(SexType sexType) {
        long status;

        try {
            controller.edit(sexType);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(SexType sexType) {
        long status;

        try {
            controller.destroy(sexType.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
