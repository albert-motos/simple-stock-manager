package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
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
    
    public EmployeeType findByType(String type) {
        EmployeeType employeeType;

        try {
            Query query = helper.getFindByType(type);
            employeeType = (EmployeeType) query.getSingleResult();
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType;
    }

    public List<EmployeeType> findAll() {
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

    public List<EmployeeType> findEnable() {
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
