package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PriceTypeTranslationJpaController;
import javax.persistence.Query;

/**
 * Helper class for PriceTypeTranslation object
 *
 * @author foxtrot
 */
public class PriceTypeTranslationHelper extends CommonHelper implements BaseTypeTranslationHelper {

    public PriceTypeTranslationHelper() {
        super(BusinessConstant.QUERY.PRICE_TYPE_TRANSLATION);
    }

    public PriceTypeTranslationJpaController getJpaController() {
        return new PriceTypeTranslationJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByTranslationAndLanguage(String translation, String language) {
        Query query = entityManager.createNamedQuery("PriceTypeTranslation.getFindByTranslationAndLanguage");
        query.setParameter("translation", translation);
        query.setParameter("language", language);
        
        return query;
    }

}
