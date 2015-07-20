package com.development.simplestockmanager.common.business.helper.base;

import javax.persistence.Query;

/**
 * Interface helper class for type object
 *
 * @author foxtrot
 */
public interface BaseTypeHelper {

    public Query getFindByType(String type);
    
    public Query getFindAll();
    
    public Query getFindEnable();
}
