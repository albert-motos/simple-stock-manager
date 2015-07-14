package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.SexTypeTranslationSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeSelector extends CommonSelector implements BaseSelector {

    private final SexTypeTranslationSpecificController specificController;
    private HashMap<String, SexType> hashMap;

    public SexTypeSelector(long mode, String language) {
        super(mode, language);
        specificController = new SexTypeTranslationSpecificController(language);
        search();
    }
    
    public SexTypeSelector(long mode, String language, SexType sexType) {
        this(mode, language);
        this.selection = getDisplayName(getTranslation(sexType));
    }    
    
    @Override
    public void search() {
        clear();
        List<SexTypeTranslation> sexTypeTranslationList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            sexTypeTranslationList = specificController.getFindAllForSelector();
        } else {
            sexTypeTranslationList = specificController.getFindEnableForSelector();
        }

        for (SexTypeTranslation sexTypeTranslation : sexTypeTranslationList) {
            String key = getDisplayName(sexTypeTranslation);
            hashMap.put(key, sexTypeTranslation.getReference());
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public SexType getSelectedValue() {
        SexType sexType = new SexTypeNull();

        if (!selection.isEmpty()) {
            sexType = hashMap.get(selection);
        }

        return sexType;
    }
    
    public String getDisplayName(SexType sexType) {
        return getDisplayName(getTranslation(sexType));
    }
    
    private String getDisplayName(SexTypeTranslation sexTypeTranslation) {
        return sexTypeTranslation.getTranslation();
    }
    
    private SexTypeTranslation getTranslation(SexType sexType) {
        SexTypeTranslation translation = new SexTypeTranslationNull();
        
        for (SexTypeTranslation sexTypeTranslation : sexType.getSexTypeTranslationList()) {
            if (sexTypeTranslation.getLanguage().getCode().equals(language)) {
                translation = sexTypeTranslation;
            }
        }
        
        return translation;
    }
    
}
