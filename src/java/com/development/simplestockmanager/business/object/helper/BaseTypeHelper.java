package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 * Interface helper class for type object
 *
 * @author foxtrot
 */
public interface BaseTypeHelper {

    public Query getFindAllForSelector(String language);
    
    public Query getFindEnableForSelector(String language);
}
