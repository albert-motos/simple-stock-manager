package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeHelper extends CommonHelper implements BaseTypeHelper {

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

    

    

}
