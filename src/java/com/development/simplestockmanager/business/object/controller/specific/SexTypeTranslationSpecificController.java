package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.SexTypeTranslationHelper;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import javax.persistence.Query;

/**
 * Specific controller class for SexTypeTranslation object
 *
 * @author foxtrot
 */
public class SexTypeTranslationSpecificController {

    private final SexTypeTranslationHelper helper;

    public SexTypeTranslationSpecificController() {
        this.helper = new SexTypeTranslationHelper();
    }

    public SexTypeTranslation findByTranslationAndLanguage(String translation, String language) {
        SexTypeTranslation employeeTypeTranslation;
        
        try {
            Query query = helper.getFindByTranslationAndLanguage(translation, language);
            employeeTypeTranslation = (SexTypeTranslation) query.getSingleResult();
        } catch (Exception e) {
            employeeTypeTranslation = new SexTypeTranslationNull();
        }
        
        return employeeTypeTranslation;
    }    
    
}
