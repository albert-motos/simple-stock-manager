package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.EmployeeType;

/**
 * Null class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeNull extends EmployeeType {

    public EmployeeTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
