package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.InvoiceJpaController;

/**
 * Helper class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceHelper extends CommonHelper {

    public InvoiceHelper() {
        super(BusinessConstant.QUERY.INVOICE);
    }

    public InvoiceJpaController getJpaController() {
        return new InvoiceJpaController(entityManagerFactory);
    }
}
