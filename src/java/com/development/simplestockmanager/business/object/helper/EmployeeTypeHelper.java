package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeHelper extends CommonHelper implements BaseTypeHelper {

    public EmployeeTypeHelper() {
        super(BusinessConstant.QUERY.EMPLOYEE_TYPE);
    }

    public EmployeeTypeJpaController getJpaController() {
        return new EmployeeTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("EmployeeType.findByType");
        query.setParameter("type", type);

        return query;
    }
    
    @Override
    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("EmployeeType.findAll");

        return query;
    }

    @Override
    public Query getFindEnable() {
        Query query = entityManager.createNamedQuery("EmployeeType.findByEnable");
        query.setParameter("enable", true);

        return query;
    }

    public Query getFindForBrowserQuery(String type, String translation, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT distinct(e) FROM EmployeeType e INNER JOIN e.employeeTypeTranslationList AS et where 1 = 1"
                + (type.isEmpty() ? "" : " AND e.type LIKE '%" + type + "%'")
                + (translation.isEmpty() ? "" : " AND et.translation LIKE '%" + translation + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND e.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND e.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }

}
