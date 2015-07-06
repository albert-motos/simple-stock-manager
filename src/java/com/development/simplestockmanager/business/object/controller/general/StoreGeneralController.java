package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.StoreNull;
import com.development.simplestockmanager.business.object.helper.StoreHelper;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.business.persistence.controller.StoreJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class StoreGeneralController {

    public long create(Store store) {
        try {
            StoreJpaController storeJpaController = StoreHelper.getJpaController();
            storeJpaController.create(store);
        } catch (Exception e) {
            store = new StoreNull();
        }

        return store.getId();
    }

    public Store read(Store store) {
        try {
            Query query = StoreHelper.getFindByIdQuery(store.getId());
            store = (Store) query.getSingleResult();
        } catch (Exception e) {
            store = new StoreNull();
        }

        return store;
    }

    public long update(Store store) {
        long status = BusinessConstant.UPDATE.FAILURE;

        if (read(store).getId() != BusinessConstant.IDENTIFIER.INVALID) {
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.edit(store);
                status = BusinessConstant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public long delete(Store store) {
        long status = BusinessConstant.DELETE.FAILURE;

        if (read(store).getId() != BusinessConstant.IDENTIFIER.INVALID) {
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.destroy(store.getId());
                status = BusinessConstant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
