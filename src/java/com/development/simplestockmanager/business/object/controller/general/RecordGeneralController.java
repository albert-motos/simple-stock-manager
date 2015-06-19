/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.RecordNull;
import com.development.simplestockmanager.business.object.helper.RecordHelper;
import com.development.simplestockmanager.business.persistence.old.Record;
import com.development.simplestockmanager.business.persistence.controller.old.RecordJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class RecordGeneralController {

    public static long create(long employeeID, long stockID, BigDecimal amount, String note, Date createdDate, Date lastModifiedDate) {

        Record record = new Record(employeeID, stockID, amount, note, createdDate, lastModifiedDate);

        try {
            RecordJpaController recordJpaController = RecordHelper.getJpaController();
            recordJpaController.create(record);
        } catch (Exception e) {
            record = new RecordNull();
        }

        return record.getId();
    }

    public static Record read(long id) {
        Record record;

        try {
            Query query = RecordHelper.getFindByIdQuery(id);
            record = (Record) query.getSingleResult();
        } catch (Exception e) {
            record = new RecordNull();
        }

        return record;
    }

    public static long update(long id, long employeeID, long stockID, BigDecimal amount, String note, Date createdDate, Date lastModifiedDate) {

        long state = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Record record = new Record(id, employeeID, stockID, amount, note, createdDate, lastModifiedDate);

            try {
                RecordJpaController recordJpaController = RecordHelper.getJpaController();
                recordJpaController.edit(record);
                state = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(long id) {

        long state = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {

            try {
                RecordJpaController recordJpaController = RecordHelper.getJpaController();
                recordJpaController.destroy(id);
                state = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

}
