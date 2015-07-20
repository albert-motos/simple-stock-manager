package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeTranslationHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeTranslationJpaController;
import javax.persistence.Query;

/**
 * Helper class for EmployeeTypeTranslation object
 *
 * @author foxtrot
 */
public class EmployeeTypeTranslationHelper extends CommonHelper implements BaseTypeTranslationHelper {

    public EmployeeTypeTranslationHelper() {
        super(BusinessConstant.QUERY.EMPLOYEE_TYPE_TRANSLATION);
    }

    public EmployeeTypeTranslationJpaController getJpaController() {
        return new EmployeeTypeTranslationJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByTranslationAndLanguage(String translation, String language) {
        Query query = entityManager.createNamedQuery("EmployeeTypeTranslation.findByTranslationAndLanguage");
        query.setParameter("translation", translation);
        query.setParameter("language", language);
        
        return query;
    }

}
