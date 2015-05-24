/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.RecordNull;
import com.simplestockmanager.helper.RecordHelper;
import com.simplestockmanager.persistence.Record;
import com.simplestockmanager.persistence.controller.RecordJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
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

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Record record = new Record(id, employeeID, stockID, amount, note, createdDate, lastModifiedDate);

            try {
                RecordJpaController recordJpaController = RecordHelper.getJpaController();
                recordJpaController.edit(record);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                RecordJpaController recordJpaController = RecordHelper.getJpaController();
                recordJpaController.destroy(id);
                state = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

}
