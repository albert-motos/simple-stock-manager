package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class LanguageTypeHelper {

    public Query getFindAll() {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("EmployeeType.findAll");

        return query;
    }

}
