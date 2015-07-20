package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.InvoiceNull;
import com.development.simplestockmanager.business.object.helper.InvoiceHelper;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.controller.InvoiceJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceGeneralController {

    private final InvoiceJpaController controller;

    public InvoiceGeneralController() {
        InvoiceHelper helper = new InvoiceHelper();
        controller = helper.getJpaController();
    }

    public long create(Invoice invoice) {
        try {
            controller.create(invoice);
        } catch (Exception e) {
            invoice = new InvoiceNull();
        }

        return invoice.getId();
    }

    public Invoice read(Invoice invoice) {
        try {
            invoice = controller.findInvoice(invoice.getId());

            if (invoice == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            invoice = new InvoiceNull();
        }

        return invoice;
    }

//    
    public long update(Invoice invoice) {
        long status;

        try {
            controller.edit(invoice);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Invoice invoice) {
        long status;

        try {
            controller.destroy(invoice.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
