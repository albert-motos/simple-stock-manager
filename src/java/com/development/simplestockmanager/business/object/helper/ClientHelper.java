package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.ClientJpaController;
import javax.persistence.Query;

/**
 * Helper class for Client object
 *
 * @author foxtrot
 */
public class ClientHelper {

    public static ClientJpaController getJpaController() {
        return new ClientJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Client.findById");
        query.setParameter("id", id);

        return query;
    }
}
