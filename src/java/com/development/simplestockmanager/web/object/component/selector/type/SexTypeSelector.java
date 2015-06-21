package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeSelector extends BaseTypeSelector {

    private final HashMap<String, SexType> hashMap;

    public SexTypeSelector(LanguageType languageType) {
        SexTypeSpecificController controller = new SexTypeSpecificController(languageType);
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (SexType sexType : controller.fillSelector()) {
            String key = sexType.getType();
            hashMap.put(key, sexType);
            list.add(key);
        }
    }

    public SexType getSelectedValue() {
        SexType sexType = new SexTypeNull();

        if (!selection.isEmpty()) {
            sexType = hashMap.get(selection);
        }

        return sexType;
    }

}
