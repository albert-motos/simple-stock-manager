package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import javax.persistence.Query;

/**
 * Helper class for Language object
 *
 * @author foxtrot
 */
public class LanguageHelper extends CommonHelper {

    public LanguageHelper() {
        super(BusinessConstant.QUERY.LANGUAGE);
    }

    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("Language.findAll");

        return query;
    }
    
    public Query getFindByCode(String code) {
        Query query = entityManager.createNamedQuery("Language.findByCode");
        query.setParameter("code", code);
        
        return query;
    }

}
