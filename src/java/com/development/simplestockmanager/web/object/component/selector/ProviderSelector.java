package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.ProviderSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ProviderNull;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Provider object
 *
 * @author foxtrot
 */
public class ProviderSelector extends CommonSelector implements BaseSelector {

    private final ProviderSpecificController specificController;
    private HashMap<String, Provider> hashMap;

    public ProviderSelector(long mode) {
        super(mode);
        this.specificController = new ProviderSpecificController();
        search();
    }

    public ProviderSelector(long mode, Provider provider) {
        this(mode);
        this.selection = getDisplayName(provider);
    }

    @Override
    public void search() {
        clear();
        List<Provider> providerList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            providerList = specificController.getFindAllByBrowser(browser);
        } else {
            providerList = specificController.getFindEnableByBrowser(browser);
        }

        for (Provider provider : providerList) {
            String key = getDisplayName(provider);
            hashMap.put(key, provider);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public Provider getSelectedValue() {
        Provider provider = new ProviderNull();

        if (!selection.isEmpty()) {
            provider = hashMap.get(selection);
        }

        return provider;
    }
    
    public String getDisplayName(Provider provider) {
        String name = "";
        
        if (provider != null) {
            name = provider.getName();
        }
        
        return name;
    }
    
}
