/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.LanguageType;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public interface BaseTypeHelper {
    
    public Query getFindByIdQuery(long id);
    
    public Query getFindAllForSelector(LanguageType languageType);
}
