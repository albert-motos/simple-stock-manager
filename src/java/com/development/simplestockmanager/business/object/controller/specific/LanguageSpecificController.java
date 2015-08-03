package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.LanguageHelper;
import com.development.simplestockmanager.business.object.nullpackage.LanguageNull;
import com.development.simplestockmanager.business.persistence.Language;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Language object
 *
 * @author foxtrot
 */
public class LanguageSpecificController {

    private final LanguageHelper helper;

    public LanguageSpecificController() {
        helper = new LanguageHelper();
    }

    public List<Language> findAll() {
        List<Language> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((Language) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public Language findByCode(String code) {
        Language language;

        try {
            Query query = helper.getFindByCode(code);
            language = (Language) query.getSingleResult();
        } catch (Exception e) {
            language = new LanguageNull();
        }
        
        return language;
    }

}
