package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 * Helper class for LanguageType object
 *
 * @author foxtrot
 */
public class LanguageHelper extends BaseHelper {

    public Query getFindAllForSelector(String language) {
        Query query = entityManager.createNamedQuery("LanguageType.findAllForSelector");
        query.setParameter("language", language);

        return query;
    }

}
