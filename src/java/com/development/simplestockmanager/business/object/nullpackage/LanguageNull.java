package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Language;

/**
 * Null class for Language object
 *
 * @author foxtrot
 */
public class LanguageNull extends Language {

    public LanguageNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
