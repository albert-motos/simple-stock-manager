package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Invoice;

/**
 * Null class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceNull extends Invoice {

    public InvoiceNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
