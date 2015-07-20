package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PaymentTypeTranslationHelper;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import javax.persistence.Query;

/**
 * Specific controller class for PaymentTypeTranslation object
 *
 * @author foxtrot
 */
public class PaymentTypeTranslationSpecificController {

    private final PaymentTypeTranslationHelper helper;

    public PaymentTypeTranslationSpecificController() {
        this.helper = new PaymentTypeTranslationHelper();
    }

    public PaymentTypeTranslation findByTranslationAndLanguage(String translation, String language) {
        PaymentTypeTranslation employeeTypeTranslation;
        
        try {
            Query query = helper.getFindByTranslationAndLanguage(translation, language);
            employeeTypeTranslation = (PaymentTypeTranslation) query.getSingleResult();
        } catch (Exception e) {
            employeeTypeTranslation = new PaymentTypeTranslationNull();
        }
        
        return employeeTypeTranslation;
    }    
    
}
