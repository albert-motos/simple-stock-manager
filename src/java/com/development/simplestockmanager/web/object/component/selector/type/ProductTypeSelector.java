package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.ProductTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeNull;
import com.development.simplestockmanager.business.persistence.ProductType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Selector class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeSelector extends BaseTypeSelector {

    private final HashMap<String, ProductType> hashMap;

    public ProductTypeSelector(String language) {
        ProductTypeSpecificController controller = new ProductTypeSpecificController(language);
        hashMap = new HashMap<>();
        list = new ArrayList<>();

        for (ProductType productType : controller.fillSelector()) {
            String key = productType.getType();
            hashMap.put(key, productType);
            list.add(key);
        }
    }

    public ProductType getSelectedValue() {
        ProductType productType = new ProductTypeNull();
        
        if (!selection.isEmpty()) {
            productType = hashMap.get(selection);
        }

        return productType;
    }

}
