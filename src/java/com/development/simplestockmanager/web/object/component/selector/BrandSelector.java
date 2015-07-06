package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.persistence.Brand;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for Brand object
 *
 * @author foxtrot
 */
public class BrandSelector extends BaseSelector {

    private HashMap<String, Brand> hashMap;
    private final BrandSpecificController controller;

    public BrandSelector() {
        controller = new BrandSpecificController();
        hashMap = new HashMap<>();
        list = new ArrayList<>();
    }

    @Override
    public void find() {
        System.out.println("# " + browser);
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (Brand brand : controller.fillSelectorByName(browser)) {
            String key = brand.getName();
            hashMap.put(key, brand);
            list.add(key);
        }
        System.out.println("# " + list);
    }

    public Brand getSelectedValue() {
        Brand brand = new BrandNull();

        if (!selection.isEmpty()) {
            brand = hashMap.get(selection);
        }

        return brand;
    }

}
