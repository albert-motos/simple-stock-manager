package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.LanguageSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.LanguageNull;
import com.development.simplestockmanager.business.object.nullpackage.LanguageTranslationNull;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.LanguageTranslation;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.component.selector.BaseSelector;
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

    public LanguageSelector(long mode, String language) {
        super(mode, language);
        specificController = new LanguageSpecificController();
        search();
    }
    
    public LanguageSelector(long mode, String language, Language languageObject) {
        this(mode, language);
        this.selection = getDisplayName(getTranslation(languageObject));
    }

    @Override
    public void search() {
        clear();
        
        for (Language languageTranslation : specificController.getFindAll()) {
            String key = getDisplayName(languageTranslation);
            hashMap.put(key, languageTranslation);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
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
