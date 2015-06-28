package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.LanguageTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.LanguageTypeNull;
import com.development.simplestockmanager.business.persistence.LanguageType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for LanguageType object
 *
 * @author foxtrot
 */
public class LanguageTypeSelector extends BaseTypeSelector {

    private final HashMap<String, LanguageType> hashMap;

    public LanguageTypeSelector() {
        LanguageTypeSpecificController controller = new LanguageTypeSpecificController();
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (LanguageType languageType : controller.fillSelector()) {
            String key = languageType.getLanguage();
            hashMap.put(key, languageType);
            list.add(key);
        }
    }

    public LanguageType getSelectedValue() {
        LanguageType languageType = new LanguageTypeNull();

        if (!selection.isEmpty()) {
            languageType = hashMap.get(selection);
        }

        return languageType;
    }

}
