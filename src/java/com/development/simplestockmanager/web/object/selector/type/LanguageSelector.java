package com.development.simplestockmanager.web.object.selector.type;

import com.development.simplestockmanager.common.web.object.selector.common.CommonTypeSelector;
import com.development.simplestockmanager.business.object.controller.specific.LanguageSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.LanguageNull;
import com.development.simplestockmanager.business.object.nullpackage.LanguageTranslationNull;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.LanguageTranslation;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for Language object
 *
 * @author foxtrot
 */
public class LanguageSelector extends CommonTypeSelector implements BaseSelector {

    private final LanguageSpecificController specificController;
    private HashMap<String, Language> hashMap;

    public LanguageSelector(long mode) {
        super(mode);
        specificController = new LanguageSpecificController();
        clear();
    }
    
    public LanguageSelector(long mode, Language languageObject) {
        this(mode);
        this.selection = getDisplayName(getTranslation(languageObject));
    }

    @Override
    public void search() {        
        for (Language languageTranslation : specificController.findAll()) {
            String key = getDisplayName(languageTranslation);
            hashMap.put(key, languageTranslation);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        selection = "";
        
        search();
    }

    public Language getSelectedValue() {
        Language languageBase = new LanguageNull();

        if (!selection.isEmpty()) {
            languageBase = hashMap.get(selection);
        }

        return languageBase;
    }

    public String getDisplayName(Language objectLanguage) {
        return (objectLanguage != null ? getDisplayName(getTranslation(objectLanguage)) : "");
    }
    
    private String getDisplayName(LanguageTranslation languageTranslation) {
        return languageTranslation.getTranslation();
    }
    
    private LanguageTranslation getTranslation(Language objectLanguage) {
        LanguageTranslation translation = new LanguageTranslationNull();
        
        for (LanguageTranslation languageTranslation : objectLanguage.getLanguageTranslationList1()) {
            if (languageTranslation.getLanguage().getCode().equals(language)) {
                translation = languageTranslation;
            }
        }
        
        return translation;
    }
}
