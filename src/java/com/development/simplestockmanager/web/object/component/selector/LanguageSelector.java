package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.LanguageSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.LanguageNull;
import com.development.simplestockmanager.business.persistence.Language;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for LanguageType object
 *
 * @author foxtrot
 */
public class LanguageSelector extends BaseSelector {

    private final LanguageSpecificController specificController;
    private final HashMap<String, Language> hashMap;

    public LanguageSelector(String language) {
        specificController = new LanguageSpecificController(language);
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        find();
    }

    @Override
    public final void find() {
        for (Language language : specificController.getFindAllForSelector()) {
            String key = getDisplayName(language);
            hashMap.put(key, language);
            list.add(key);
        }
    }

    public Language getSelectedValue() {
        Language language = new LanguageNull();

        if (!selection.isEmpty()) {
            language = hashMap.get(selection);
        }

        return language;
    }

    public String getDisplayName(Language language) {
        return language.getLanguage();
    }

}
