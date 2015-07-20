package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.ItemJpaController;

/**
 * Helper class for Item object
 *
 * @author foxtrot
 */
public class ItemHelper extends CommonHelper {

    public ItemHelper() {
        super(BusinessConstant.QUERY.ITEM);
    }

    public ItemJpaController getJpaController() {
        return new ItemJpaController(entityManagerFactory);
    }
}
