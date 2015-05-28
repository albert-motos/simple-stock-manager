/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.InvoiceNull;
import com.simplestockmanager.helper.InvoiceHelper;
import com.simplestockmanager.persistence.Invoice;
import com.simplestockmanager.persistence.controller.InvoiceJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class InvoiceGeneralController {

    public static long create(long clientID, long employeeID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate,
            long analitycsTimeID) {

        Invoice invoice = new Invoice(clientID, employeeID, paymentTypeID, cost, createdDate, lastModifiedDate, analitycsTimeID);

        try {
            InvoiceJpaController invoiceJpaController = InvoiceHelper.getJpaController();
            invoiceJpaController.create(invoice);
        } catch (Exception e) {
            invoice = new InvoiceNull();
        }

        return invoice.getId();
    }

    public static Invoice read(long id) {

        Invoice invoice;

        try {
            Query query = InvoiceHelper.getFindByIdQuery(id);
            invoice = (Invoice) query.getSingleResult();
        } catch (Exception e) {
            invoice = new InvoiceNull();
        }

        return invoice;
    }

    public static long update(long id, long clientID, long employeeID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate,
            long analitycsTimeID) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Invoice invoice = new Invoice(id, clientID, employeeID, paymentTypeID, cost, createdDate, lastModifiedDate, analitycsTimeID);

            try {
                InvoiceJpaController invoiceJpaController = InvoiceHelper.getJpaController();
                invoiceJpaController.edit(invoice);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                InvoiceJpaController invoiceJpaController = InvoiceHelper.getJpaController();
                invoiceJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
