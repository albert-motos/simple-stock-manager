package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Common helper class
 *
 * @author foxtrot
 */
public class CommonHelper {
    
    private final String type;
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public CommonHelper(String type) {
        this.type = type;
        entityManagerFactory = Persistence.createEntityManagerFactory(BusinessConstant.PROJECT.PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public String getAuditoryQuery(Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {

        String query = "";

        if (createdDateFrom != null || createdDateTo != null) {
            if (createdDateFrom == null) {
                createdDateFrom = new Date(0);
            }

            if (createdDateTo == null) {
                createdDateTo = new Date();
            }

            query = query.concat(" AND " + type + ".createdDate BETWEEN '" + new Timestamp(createdDateFrom.getTime())
                    + "' AND '" + new Timestamp(createdDateTo.getTime()) + "'");
        }

        if (lastModifiedDateFrom != null || lastModifiedDateTo != null) {
            if (lastModifiedDateFrom == null) {
                lastModifiedDateFrom = new Date(0);
            }

            if (lastModifiedDateTo == null) {
                lastModifiedDateTo = new Date();
            }

            query = query.concat(" AND " + type + ".lastModifiedDate BETWEEN '" + new Timestamp(lastModifiedDateFrom.getTime())
                    + "' AND '" + new Timestamp(lastModifiedDateTo.getTime()) + "'");
        }

        if (createdUserID != BusinessConstant.IDENTIFIER.INVALID) {
            query = query.concat(" AND " + type + ".createdUser.id = " + createdUserID);
        }

        if (lastModifiedUserID != BusinessConstant.IDENTIFIER.INVALID) {
            query = query.concat(" AND " + type + ".lastModifiedUser.id = " + lastModifiedUserID);
        }

        return query;
    }
}
