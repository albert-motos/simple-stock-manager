package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.StoreNull;
import com.development.simplestockmanager.business.object.helper.StoreHelper;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.business.persistence.controller.StoreJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class StoreGeneralController {

    private final StoreJpaController controller;

    public StoreGeneralController() {
        StoreHelper helper = new StoreHelper();
        controller = helper.getJpaController();
    }

    public long create(Store store) {
        try {
            controller.create(store);
        } catch (Exception e) {
            store = new StoreNull();
        }

        return store.getId();
    }

    public Store read(Store store) {
        try {
            store = controller.findStore(store.getId());

            if (store == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            store = new StoreNull();
        }

        return store;
    }

//    
    public long update(Store store) {
        long status;

        try {
            controller.edit(store);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Store store) {
        long status;

        try {
            controller.destroy(store.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
