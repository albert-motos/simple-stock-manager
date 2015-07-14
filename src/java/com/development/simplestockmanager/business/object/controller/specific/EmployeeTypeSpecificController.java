package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeSpecificController {

    private final EmployeeTypeHelper helper;

    public EmployeeTypeSpecificController() {
        this.helper = new EmployeeTypeHelper();
    }

    public List<EmployeeType> getFindAll() {
        List<EmployeeType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((EmployeeType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<EmployeeType> getFindEnable() {
        List<EmployeeType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((EmployeeType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
