package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.EmployeeType;

/**
 * Null class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeNull extends EmployeeType {

    public EmployeeTypeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
