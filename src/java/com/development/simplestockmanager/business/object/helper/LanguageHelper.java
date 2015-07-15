package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 * Helper class for Language object
 *
 * @author foxtrot
 */
public class LanguageHelper extends CommonHelper {

    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("Language.findAll");

        return query;
    }

}
