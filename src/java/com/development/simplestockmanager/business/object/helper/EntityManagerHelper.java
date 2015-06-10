/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.Constant;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author foxtrot
 */
public class EntityManagerHelper {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(Constant.PROJECT.PERSISTENCE_UNIT);
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

}
