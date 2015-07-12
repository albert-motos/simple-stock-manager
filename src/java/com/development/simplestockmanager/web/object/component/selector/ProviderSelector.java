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
public class ProviderSelector extends BaseSelector {

    private HashMap<String, Provider> hashMap;
    private ProviderSpecificController specificController;

    private ProviderSelector() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    public ProviderSelector(long mode, ProviderSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;
    }

    public ProviderSelector(long mode, Provider provider, ProviderSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;

        String key = provider.getName();
        hashMap.put(key, provider);
        list.add(key);
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        
        List<Provider> providerList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            providerList = specificController.fillSelector();
        } else {
            providerList = specificController.fillSelectorByName(browser);
        }

        for (Provider provider : providerList) {
            String key = provider.getName();
            hashMap.put(key, provider);
            list.add(key);
        }
    }

    public Provider getSelectedValue() {
        Provider provider = new ProviderNull();

        if (!selection.isEmpty()) {
            provider = hashMap.get(selection);
        }

        return provider;
    }

}
