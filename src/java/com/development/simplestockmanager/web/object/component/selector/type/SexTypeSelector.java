package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeSelector extends BaseTypeSelector {

    private HashMap<String, SexType> hashMap;

    private SexTypeSelector(long mode, SexTypeSpecificController specificController) {
        list = new ArrayList<>();
        hashMap = new HashMap<>();

        List<SexType> sexTypeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            sexTypeList = specificController.fillAllForSelector();
        } else {
            sexTypeList = specificController.fillEnableForSelector();
        }

        for (SexType sexType : sexTypeList) {
//            String key = sexType.getType();
//            hashMap.put(key, sexType.getReferencedType());
//            list.add(key);
        }
    }

    public SexTypeSelector(long mode, String language) {
        this(mode, new SexTypeSpecificController(language));
    }

    public SexTypeSelector(long mode, SexType sexType, String language) {
        this(mode, new SexTypeSpecificController(language));

//        for (SexType sexTypeLanguage : sexType.getSexTypeList()) {
//            if (sexTypeLanguage.getLanguageType().getCode().equals(language)) {
//                String key = sexTypeLanguage.getType();
//                selection = key;
//            }
//        }

    }

    public SexType getSelectedValue() {
        SexType sexType = new SexTypeNull();

        if (!selection.isEmpty()) {
            sexType = hashMap.get(selection);
        }

        return sexType;
    }
    
    public SexType getValueForLanguage(SexType sexTypeBase, String language) {
        SexType translation = new SexTypeNull();
        
//        for (SexType sexType : sexTypeBase.getSexTypeList()) {
//            if (sexType.getLanguageType().getCode().equals(language)) {
//                translation = sexType;
//            }
//        }
        
        return translation;
    };

}
