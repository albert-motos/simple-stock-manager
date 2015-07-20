package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;

/**
 * Null class for SexTypeTranslation object
 *
 * @author foxtrot
 */
public class SexTypeTranslationNull extends SexTypeTranslation {

    public SexTypeTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
