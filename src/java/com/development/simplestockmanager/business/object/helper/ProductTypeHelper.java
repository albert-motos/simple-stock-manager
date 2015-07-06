package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ProductTypeHelper implements BaseTypeHelper{

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("ProductType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
