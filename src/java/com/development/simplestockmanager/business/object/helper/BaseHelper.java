package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Common helper class
 *
 * @author foxtrot
 */
public class BaseHelper {
    
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public BaseHelper() {
        entityManagerFactory = Persistence.createEntityManagerFactory(BusinessConstant.PROJECT.PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public String getAuditoryQuery(String type, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {

        String query = "";

        if (createdDateFrom != null || createdDateTo != null) {
            if (createdDateFrom == null) {
                createdDateFrom = new Date(0);
            }

            if (createdDateTo == null) {
                createdDateTo = new Date();
            }

            query = query.concat(" " + type + ".createdDate BETWEEN '" + createdDateFrom + "' AND '" + createdDateTo + "'");
        }

        if (lastModifiedDateFrom != null || lastModifiedDateTo != null) {
            if (lastModifiedDateFrom == null) {
                lastModifiedDateFrom = new Date(0);
            }

            if (lastModifiedDateTo == null) {
                lastModifiedDateTo = new Date();
            }

            query = query.concat(" " + type + ".lastModifiedDate BETWEEN '" + lastModifiedDateFrom + "' AND '" + lastModifiedDateTo + "'");
        }

        if (createdUserID != BusinessConstant.IDENTIFIER.INVALID) {
            query = query.concat(" " + type + ".createdUser.id = " + createdUserID);
        }

        if (lastModifiedUserID != BusinessConstant.IDENTIFIER.INVALID) {
            query = query.concat(" " + type + ".lastModifiedUser.id = " + lastModifiedUserID);
        }

        return query;
    }
}
