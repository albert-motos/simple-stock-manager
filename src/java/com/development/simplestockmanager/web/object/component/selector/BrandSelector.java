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
public class BrandSelector extends CommonSelector implements BaseSelector {

    private final BrandSpecificController specificController;
    private HashMap<String, Brand> hashMap;

    public BrandSelector(long mode) {
        super(mode);
        this.specificController = new BrandSpecificController();
        search();
    }

    public BrandSelector(long mode, Brand brand) {
        this(mode);
        this.selection = getDisplayName(brand);
    }

    @Override
    public void search() {
        clear();
        List<Brand> brandList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            brandList = specificController.getFindAllByBrowser(browser);
        } else {
            brandList = specificController.getFindEnableByBrowser(browser);
        }

        for (Brand brand : brandList) {
            String key = getDisplayName(brand);
            hashMap.put(key, brand);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public Brand getSelectedValue() {
        Brand brand = new BrandNull();

        if (!selection.isEmpty()) {
            brand = hashMap.get(selection);
        }

        return brand;
    }
    
    public String getDisplayName(Brand brand) {
        String name = "";
        
        if (brand != null) {
            name = brand.getName();
        }
        
        return name;
    }

}
