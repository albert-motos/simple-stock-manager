package com.development.simplestockmanager.web.object.selector.type;

import com.development.simplestockmanager.common.web.object.selector.common.CommonTypeSelector;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeSelector extends CommonTypeSelector implements BaseSelector {

    private final PriceTypeSpecificController specificController;
    private HashMap<String, PriceType> hashMap;

    public PriceTypeSelector(long mode) {
        super(mode);
        this.specificController = new PriceTypeSpecificController();
        clear();
    }

    public PriceTypeSelector(long mode, PriceType employeeType) {
        this(mode);
        this.selection = getDisplayName(getTranslation(employeeType));
    }

    @Override
    public void search() {
        List<PriceType> employeeTypeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            employeeTypeList = specificController.findAll();
        } else {
            employeeTypeList = specificController.findEnable();
        }

        for (PriceType employeeType : employeeTypeList) {
            String key = getDisplayName(getTranslation(employeeType));
            hashMap.put(key, employeeType);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
        selection = "";
        
        search();
    }
    
    public PriceType getSelectedValue() {
        PriceType employeeType = new PriceTypeNull();

        if (!selection.isEmpty()) {
            employeeType = hashMap.get(selection);
        }

        return employeeType;
    }

    public String getDisplayName(PriceType employeeType) {
        return (employeeType != null ? getDisplayName(getTranslation(employeeType)) : "");
    }
    
    private String getDisplayName(PriceTypeTranslation employeeTypeTranslation) {
        return employeeTypeTranslation.getTranslation();
    }
    
    private PriceTypeTranslation getTranslation(PriceType employeeType) {
        PriceTypeTranslation translation = new PriceTypeTranslationNull();
        
        for (PriceTypeTranslation employeeTypeTranslation : employeeType.getPriceTypeTranslationList()) {
            if (employeeTypeTranslation.getLanguage().getCode().equals(language)) {
                translation = employeeTypeTranslation;
            }
        }
        
        return translation;
    }

}
