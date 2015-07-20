package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.RecordJpaController;

/**
 * Helper class for Record object
 *
 * @author foxtrot
 */
public class RecordHelper extends CommonHelper {

    public RecordHelper() {
        super(BusinessConstant.QUERY.RECORD);
    }

    public RecordJpaController getJpaController() {
        return new RecordJpaController(entityManagerFactory);
    }
}
