package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.ProviderSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ProviderNull;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for Provider object
 *
 * @author foxtrot
 */
public class ProviderSelector extends BaseSelector {

    private HashMap<String, Provider> hashMap;
    private final ProviderSpecificController controller;

    public ProviderSelector() {
        controller = new ProviderSpecificController();
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (Provider provider : controller.fillSelectorByName(browser)) {
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
