package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Employee;

/**
 * Null class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeNull extends Employee {

    public EmployeeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
}
