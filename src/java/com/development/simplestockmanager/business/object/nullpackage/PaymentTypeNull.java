package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.PaymentType;

/**
 * Null class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeNull extends PaymentType {

    public PaymentTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
