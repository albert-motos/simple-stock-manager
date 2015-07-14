package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.SexTypeTranslationHelper;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class SexTypeSpecificController {
    
    private final String language;

    public SexTypeSpecificController(String language) {
        this.language = language;
    }

    public List<SexType> fillAllForSelector() {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = new SexTypeTranslationHelper().getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<SexType> fillEnableForSelector() {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = new SexTypeTranslationHelper().getFindEnableForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
