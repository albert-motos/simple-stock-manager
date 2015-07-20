package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductTypeTranslationHelper;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import javax.persistence.Query;

/**
 * Specific controller class for ProductTypeTranslation object
 *
 * @author foxtrot
 */
public class ProductTypeTranslationSpecificController {

    private final ProductTypeTranslationHelper helper;

    public ProductTypeTranslationSpecificController() {
        this.helper = new ProductTypeTranslationHelper();
    }

    public ProductTypeTranslation findByTranslationAndLanguage(String translation, String language) {
        ProductTypeTranslation employeeTypeTranslation;
        
        try {
            Query query = helper.getFindByTranslationAndLanguage(translation, language);
            employeeTypeTranslation = (ProductTypeTranslation) query.getSingleResult();
        } catch (Exception e) {
            employeeTypeTranslation = new ProductTypeTranslationNull();
        }
        
        return employeeTypeTranslation;
    }    
    
}
