package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class BrandHelper {

    public BrandJpaController getJpaController() {
        return new BrandJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public Query getFindByNameForSelectorQuery(String name) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Brand.getFindByNameForSelector");
        query.setParameter("name","%" + name + "%");
        System.out.println("# " + query.toString());
        System.out.println("# " + query.getParameters());
        
        return query;
    }
}
