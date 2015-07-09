package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeHelper extends BaseHelper implements BaseTypeHelper {

    public EmployeeTypeJpaController getJpaController() {
        return new EmployeeTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("EmployeeType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
