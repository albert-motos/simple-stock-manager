package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.EmployeeTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeSelector extends BaseTypeSelector {

    private final HashMap<String, EmployeeType> hashMap;

    public EmployeeTypeSelector(String language) {
        EmployeeTypeSpecificController controller = new EmployeeTypeSpecificController(language);
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (EmployeeType employeeType : controller.fillSelector()) {
            String key = employeeType.getType();
            hashMap.put(key, employeeType);
            list.add(key);
        }
    }

    public EmployeeType getSelectedValue() {
        EmployeeType employeeType = new EmployeeTypeNull();

        if (!selection.isEmpty()) {
            employeeType = hashMap.get(selection);
        }

        return employeeType;
    }

}
