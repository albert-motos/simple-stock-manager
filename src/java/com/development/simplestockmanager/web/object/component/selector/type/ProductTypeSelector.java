package com.development.simplestockmanager.web.object.component.selector.type;

import com.development.simplestockmanager.business.object.controller.specific.ProductTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.component.selector.BaseSelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeSelector extends CommonTypeSelector implements BaseSelector {

    private final ProductTypeSpecificController specificController;
    private HashMap<String, ProductType> hashMap;

    public ProductTypeSelector(long mode, String language) {
        super(mode, language);
        this.specificController = new ProductTypeSpecificController();
        search();
    }
    
    public ProductTypeSelector(long mode, String language, ProductType productType) {
        this(mode, language);
        this.selection = getDisplayName(getTranslation(productType));
    }    
    
    @Override
    public void search() {
        clear();
        List<ProductType> productTypeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            productTypeList = specificController.findAll();
        } else {
            productTypeList = specificController.findEnable();
        }

        for (ProductType productType : productTypeList) {
            String key = getDisplayName(getTranslation(productType));
            hashMap.put(key, productType);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public ProductType getSelectedValue() {
        ProductType productType = new ProductTypeNull();

        if (!selection.isEmpty()) {
            productType = hashMap.get(selection);
        }

        return productType;
    }
    
    public String getDisplayName(ProductType productType) {
        return (productType != null ? getDisplayName(getTranslation(productType)) : "");
    }
    
    private String getDisplayName(ProductTypeTranslation productTypeTranslation) {
        return productTypeTranslation.getTranslation();
    }
    
    private ProductTypeTranslation getTranslation(ProductType productType) {
        ProductTypeTranslation translation = new ProductTypeTranslationNull();
        
        for (ProductTypeTranslation productTypeTranslation : productType.getProductTypeTranslationList()) {
            if (productTypeTranslation.getLanguage().getCode().equals(language)) {
                translation = productTypeTranslation;
            }
        }
        
        return translation;
    }
    
}
