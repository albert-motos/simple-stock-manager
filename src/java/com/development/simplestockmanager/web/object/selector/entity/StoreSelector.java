package com.development.simplestockmanager.web.object.selector.entity;

import com.development.simplestockmanager.common.web.object.selector.common.CommonSelector;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import com.development.simplestockmanager.business.object.controller.specific.StoreSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.StoreNull;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Store object
 *
 * @author foxtrot
 */
public class StoreSelector extends CommonSelector implements BaseSelector {

    private final StoreSpecificController specificController;
    private HashMap<String, Store> hashMap;

    public StoreSelector(long mode) {
        super(mode);
        this.specificController = new StoreSpecificController();
        search();
    }

    public StoreSelector(long mode, Store store) {
        this(mode);
        this.selection = getDisplayName(store);
    }

    @Override
    public void search() {
        clear();
        List<Store> storeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            storeList = specificController.findAllByBrowser(browser);
        } else {
            storeList = specificController.findEnableByBrowser(browser);
        }

        for (Store store : storeList) {
            String key = getDisplayName(store);
            hashMap.put(key, store);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public Store getSelectedValue() {
        Store store = new StoreNull();

        if (!selection.isEmpty()) {
            store = hashMap.get(selection);
        }

        return store;
    }
    
    public String getDisplayName(Store store) {
        String name = "";
        
        if (store != null) {
            name = store.getName();
        }
        
        return name;
    }

}
