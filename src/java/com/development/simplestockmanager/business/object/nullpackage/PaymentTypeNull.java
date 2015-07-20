package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PaymentType;

/**
 * Null class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeNull extends PaymentType {

    public PaymentTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
