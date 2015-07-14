package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.SexTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class SexTypeTranslationSpecificController {
    
    private final SexTypeTranslationHelper helper;
    private final String language;

    public SexTypeTranslationSpecificController(String language) {
        this.language = language;
        this.helper = new SexTypeTranslationHelper();
    }

    public List<SexTypeTranslation> getFindAllForSelector() {
        List<SexTypeTranslation> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((SexTypeTranslation) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<SexTypeTranslation> getFindEnableForSelector() {
        List<SexTypeTranslation> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((SexTypeTranslation) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
