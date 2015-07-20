package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.ProductGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ProductSpecificController;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import com.development.simplestockmanager.web.object.selector.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.BrandSelector;
import com.development.simplestockmanager.web.object.validator.ProductValidator;

/**
 * Common controller class for Product object
 *
 * @author foxtrot
 */
public class ProductCommonController extends BaseCommonController {
    
    protected final ProductValidator validator;
    protected final ProductGeneralController generalController;
    protected final ProductSpecificController specificController;
    
    protected Product product;
    protected ProductTypeSelector productTypeSelector;
    protected BrandSelector brandSelector;
    protected ProviderSelector providerSelector;

    public ProductCommonController(long mode) {
        generalController = new ProductGeneralController();
        specificController = new ProductSpecificController();
        validator = new ProductValidator(mode, languageController, specificController);
    }

    public Product getProduct() {
        return product;
    }

    public ProductTypeSelector getProductTypeSelector() {
        return productTypeSelector;
    }

    public BrandSelector getBrandSelector() {
        return brandSelector;
    }

    public ProviderSelector getProviderSelector() {
        return providerSelector;
    }

}
