package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 * Interface helper class for type translation object
 *
 * @author foxtrot
 */
public interface BaseTypeTranslationHelper {

    public Query getFindByTranslationAndLanguage(String translation, String language);
    
}
