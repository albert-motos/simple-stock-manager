package com.development.simplestockmanager.business.object.helper;

import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public interface BaseTypeHelper {
    
//    public Query getFindByIdQuery(long id);
    
    public Query getFindAllForSelector(String language);
}
