package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeTypeHelper implements BaseTypeHelper {

    public EmployeeTypeJpaController getJpaController() {
        return new EmployeeTypeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("EmployeeType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
