package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.EmployeeTypeTranslationHelper;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import javax.persistence.Query;

/**
 * Specific controller class for EmployeeTypeTranslation object
 *
 * @author foxtrot
 */
public class EmployeeTypeTranslationSpecificController {

    private final EmployeeTypeTranslationHelper helper;

    public EmployeeTypeTranslationSpecificController() {
        this.helper = new EmployeeTypeTranslationHelper();
    }

    public EmployeeTypeTranslation findByTranslationAndLanguage(String translation, String language) {
        EmployeeTypeTranslation employeeTypeTranslation;
        
        try {
            Query query = helper.getFindByTranslationAndLanguage(translation, language);
            employeeTypeTranslation = (EmployeeTypeTranslation) query.getSingleResult();
        } catch (Exception e) {
            employeeTypeTranslation = new EmployeeTypeTranslationNull();
        }
        
        return employeeTypeTranslation;
    }    
    
}
