/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.RecordJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class RecordHelper {

    public static RecordJpaController getJpaController() {
        return new RecordJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Record.findById");
        query.setParameter("id", id);

        return query;
    }
}
