package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.LanguageTypeHelper;
import com.development.simplestockmanager.business.persistence.LanguageType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class LanguageTypeSpecificController {

    private final String language;

    public LanguageTypeSpecificController(String language) {
        this.language = language;
    }

    public List<LanguageType> fillSelector() {
        List<LanguageType> list = new ArrayList<>();

        try {
            Query query = new LanguageTypeHelper().getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((LanguageType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
