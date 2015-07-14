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

    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("EmployeeType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

    public Query getFindEnableForSelector(String language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
