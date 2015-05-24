/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.ClientJpaController;
import javax.persistence.Query;

/**
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
