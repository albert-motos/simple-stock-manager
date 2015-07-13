package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Brand object
 *
 * @author foxtrot
 */
public class BrandSelector extends BaseSelector {

    private HashMap<String, Brand> hashMap;
    private BrandSpecificController specificController;

    private BrandSelector() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    public BrandSelector(long mode, BrandSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;
    }

    public BrandSelector(long mode, Brand brand, BrandSpecificController specificController) {
        this();
        this.mode = mode;
        this.specificController = specificController;

        String key = brand.getName();
        hashMap.put(key, brand);
        list.add(key);
        selection = key;
    }

    @Override
    public void find() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        
        List<Brand> brandList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            brandList = specificController.fillSelector();
        } else {
            brandList = specificController.fillSelectorByName(browser);
        }

        for (Brand brand : brandList) {
            String key = brand.getName();
            hashMap.put(key, brand);
            list.add(key);
        }
    }

    public Brand getSelectedValue() {
        Brand brand = new BrandNull();

        if (!selection.isEmpty()) {
            brand = hashMap.get(selection);
        }

        return brand;
    }

}
