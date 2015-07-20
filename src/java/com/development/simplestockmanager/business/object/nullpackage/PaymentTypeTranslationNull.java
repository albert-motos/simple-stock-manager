package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;

/**
 * Null class for PaymentTypeTranslation object
 *
 * @author foxtrot
 */
public class PaymentTypeTranslationNull extends PaymentTypeTranslation {

    public PaymentTypeTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
