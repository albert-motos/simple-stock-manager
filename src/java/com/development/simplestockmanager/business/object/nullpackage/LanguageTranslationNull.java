package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.LanguageTranslation;

/**
 * Null class for LanguageTranslation object
 *
 * @author foxtrot
 */
public class LanguageTranslationNull extends LanguageTranslation {

    public LanguageTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
