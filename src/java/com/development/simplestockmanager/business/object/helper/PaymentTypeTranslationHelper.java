package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeTranslationHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PaymentTypeTranslationJpaController;
import javax.persistence.Query;

/**
 * Helper class for PaymentTypeTranslation object
 *
 * @author foxtrot
 */
public class PaymentTypeTranslationHelper extends CommonHelper implements BaseTypeTranslationHelper {

    public PaymentTypeTranslationHelper() {
        super(BusinessConstant.QUERY.PAYMENT_TYPE_TRANSLATION);
    }

    public PaymentTypeTranslationJpaController getJpaController() {
        return new PaymentTypeTranslationJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByTranslationAndLanguage(String translation, String language) {
        Query query = entityManager.createNamedQuery("PaymentTypeTranslation.findByTranslationAndLanguage");
        query.setParameter("translation", translation);
        query.setParameter("language", language);
        
        return query;
    }

}
