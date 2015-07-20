package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeTranslationHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.ProductTypeTranslationJpaController;
import javax.persistence.Query;

/**
 * Helper class for ProductTypeTranslation object
 *
 * @author foxtrot
 */
public class ProductTypeTranslationHelper extends CommonHelper implements BaseTypeTranslationHelper {

    public ProductTypeTranslationHelper() {
        super(BusinessConstant.QUERY.PRODUCT_TYPE_TRANSLATION);
    }

    public ProductTypeTranslationJpaController getJpaController() {
        return new ProductTypeTranslationJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByTranslationAndLanguage(String translation, String language) {
        Query query = entityManager.createNamedQuery("ProductTypeTranslation.findByTranslationAndLanguage");
        query.setParameter("translation", translation);
        query.setParameter("language", language);
        
        return query;
    }

}
