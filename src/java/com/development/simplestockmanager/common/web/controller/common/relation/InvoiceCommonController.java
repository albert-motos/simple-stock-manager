package com.development.simplestockmanager.common.web.controller.common.relation;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.InvoiceGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.InvoiceSpecificController;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.web.object.selector.entity.ClientSelector;
import com.development.simplestockmanager.web.object.selector.type.PaymentTypeSelector;
import com.development.simplestockmanager.web.object.validator.relation.InvoiceValidator;

/**
 * Common controller class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceCommonController extends BaseCommonController {

    protected Invoice invoice;
    protected final InvoiceValidator validator;
    protected final InvoiceGeneralController generalController;
    protected final InvoiceSpecificController specificController;

    protected ClientSelector clientSelector;
    protected PaymentTypeSelector paymentTypeSelector;

    public InvoiceCommonController(long mode) {
        generalController = new InvoiceGeneralController();
        specificController = new InvoiceSpecificController();
        validator = new InvoiceValidator(mode);
    }

    public ClientSelector getClientSelector() {
        return clientSelector;
    }

    public void setClientSelector(ClientSelector clientSelector) {
        this.clientSelector = clientSelector;
    }

    public PaymentTypeSelector getPaymentTypeSelector() {
        return paymentTypeSelector;
    }

    public void setPaymentTypeSelector(PaymentTypeSelector paymentTypeSelector) {
        this.paymentTypeSelector = paymentTypeSelector;
    }

    public Invoice getInvoice() {
        return invoice;
    }

}
