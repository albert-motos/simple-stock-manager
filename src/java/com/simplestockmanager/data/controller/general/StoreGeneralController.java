package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.StoreNull;
import com.simplestockmanager.helper.StoreHelper;
import com.simplestockmanager.persistence.Store;
import com.simplestockmanager.persistence.controller.StoreJpaController;
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
        long status = Constant.UPDATE.FAILURE;

        if (read(store).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.edit(store);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public long delete(Store store) {
        long status = Constant.DELETE.FAILURE;

        if (read(store).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.destroy(store.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
