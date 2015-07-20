package com.development.simplestockmanager.common.business.helper.base;

import javax.persistence.Query;

/**
 * Interface helper class for type translation object
 *
 * @author foxtrot
 */
public interface BaseTypeTranslationHelper {

    public Query getFindByTranslationAndLanguage(String translation, String language);
    
}
