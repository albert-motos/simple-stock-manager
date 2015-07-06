package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.LanguageType;

/**
 * Null class for LanguageType object
 *
 * @author foxtrot
 */
public class LanguageTypeNull extends LanguageType {

    public LanguageTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
