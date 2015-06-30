package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class LanguageTypeHelper implements BaseTypeHelper{

    @Override
    public Query getFindAllForSelector(String language) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("LanguageType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
