package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PriceTypeTranslationHelper;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import javax.persistence.Query;

/**
 * Specific controller class for PriceTypeTranslation object
 *
 * @author foxtrot
 */
public class PriceTypeTranslationSpecificController {

    private final PriceTypeTranslationHelper helper;

    public PriceTypeTranslationSpecificController() {
        this.helper = new PriceTypeTranslationHelper();
    }

    public PriceTypeTranslation findByTranslationAndLanguage(String translation, String language) {
        PriceTypeTranslation employeeTypeTranslation;
        
        try {
            Query query = helper.getFindByTranslationAndLanguage(translation, language);
            employeeTypeTranslation = (PriceTypeTranslation) query.getSingleResult();
        } catch (Exception e) {
            employeeTypeTranslation = new PriceTypeTranslationNull();
        }
        
        return employeeTypeTranslation;
    }    
    
}
