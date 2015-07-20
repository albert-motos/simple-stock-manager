
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.RecordNull;
import com.development.simplestockmanager.business.object.helper.RecordHelper;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.controller.RecordJpaController;

/**
 * General controller class for Record object
 *
 * @author foxtrot
 */
public class RecordGeneralController {

    private final RecordJpaController controller;

    public RecordGeneralController() {
        RecordHelper helper = new RecordHelper();
        controller = helper.getJpaController();
    }

    public long create(Record record) {
        try {
            controller.create(record);
        } catch (Exception e) {
            record = new RecordNull();
        }

        return record.getId();
    }

    public Record read(Record record) {
        try {
            record = controller.findRecord(record.getId());

            if (record == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            record = new RecordNull();
        }

        return record;
    }

    public long update(Record record) {
        long status;

        try {
            controller.edit(record);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Record record) {
        long status;

        try {
            controller.destroy(record.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}
