package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.LanguageSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.LanguageNull;
import com.development.simplestockmanager.business.persistence.Language;
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
        specificController = new LanguageSpecificController(language);
        search();
    }
    
    public LanguageSelector(long mode, String language, Language languageBase) {
        this(mode, language);
        this.selection = getDisplayNamePrivate(getTranslation(languageBase));
    }

    @Override
    public void search() {
        clear();
        
        for (Language languageTranslation : specificController.getFindAllForSelector()) {
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

    
    public String getDisplayName(Language language, String code) {
        String translation = WebConstant.UNDEFINED;
        
        for (Language languageTranslation : language.getLanguageList()) {
            if (languageTranslation.getCode().equals(code)) {
                translation = getDisplayName(languageTranslation);
            }
        }
        
        return translation;
    }
    public String getDisplayName(Language language) {
        return (language != null ? getDisplayNamePrivate(getTranslation(language)) : "");
    }
    
    private String getDisplayNamePrivate(Language languageBase) {
        return languageBase.getLanguage();
    }
    
    private Language getTranslation(Language languageBase) {
        Language translation = new LanguageNull();
        
        for (Language languageItem : languageBase.getLanguageList()) {
            if (languageItem.getCode().equals(language)) {
                translation = languageItem;
            }
        }
        
        return translation;
    }
}
