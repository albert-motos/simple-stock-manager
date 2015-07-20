package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for EmployeeTypeType object
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
    
    public List<EmployeeType> findAllForBrowser(EmployeeType browser, EmployeeTypeTranslation translation, long status, Date createdDateFrom, Date createdDateTo,
            Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        List<EmployeeType> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getType(), translation.getTranslation(), status, createdDateFrom, createdDateTo,
                    lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((EmployeeType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
