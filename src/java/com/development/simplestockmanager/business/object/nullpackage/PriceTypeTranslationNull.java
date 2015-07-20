package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;

/**
 * Null class for PriceTypeTranslation object
 *
 * @author foxtrot
 */
public class PriceTypeTranslationNull extends PriceTypeTranslation {

    public PriceTypeTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
