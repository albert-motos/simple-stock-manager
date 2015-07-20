package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.ItemNull;
import com.development.simplestockmanager.business.object.helper.ItemHelper;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.controller.ItemJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Item object
 *
 * @author foxtrot
 */
public class ItemGeneralController {

    private final ItemJpaController controller;

    public ItemGeneralController() {
        ItemHelper helper = new ItemHelper();
        controller = helper.getJpaController();
    }

    public long create(Item item) {
        try {
            controller.create(item);
        } catch (Exception e) {
            item = new ItemNull();
        }

        return item.getId();
    }

    public Item read(Item item) {
        try {
            item = controller.findItem(item.getId());

            if (item == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            item = new ItemNull();
        }

        return item;
    }

    public long update(Item item) {
        long status;

        try {
            controller.edit(item);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Item item) {
        long status;

        try {
            controller.destroy(item.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
