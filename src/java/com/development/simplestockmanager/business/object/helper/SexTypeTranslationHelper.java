package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.SexTypeTranslationJpaController;
import javax.persistence.Query;

/**
 * Helper class for SexTypeTranslation object
 *
 * @author foxtrot
 */
public class SexTypeTranslationHelper extends CommonHelper implements BaseTypeTranslationHelper {

    public SexTypeTranslationHelper() {
        super(BusinessConstant.QUERY.SEX_TYPE_TRANSLATION);
    }

    public SexTypeTranslationJpaController getJpaController() {
        return new SexTypeTranslationJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByTranslationAndLanguage(String translation, String language) {
        Query query = entityManager.createNamedQuery("SexTypeTranslation.getFindByTranslationAndLanguage");
        query.setParameter("translation", translation);
        query.setParameter("language", language);
        
        return query;
    }

}
