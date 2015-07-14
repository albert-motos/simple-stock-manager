package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;

/**
 * Null class for EmployeeTypeTranslation object
 *
 * @author foxtrot
 */
public class EmployeeTypeTranslationNull extends EmployeeTypeTranslation {

    public EmployeeTypeTranslationNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
