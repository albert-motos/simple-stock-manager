package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.ProductTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ProductTypeSpecificController;
import com.development.simplestockmanager.business.persistence.ProductType;
//import com.development.simplestockmanager.web.object.validator.ProductTypeValidator;

/**
 * Common controller class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeCommonController extends BaseCommonController {

//    protected final ProductTypeValidator validator;
    protected final ProductTypeGeneralController generalController;
    protected final ProductTypeSpecificController specificController;

    protected ProductType productType;

    public ProductTypeCommonController(long mode) {
        generalController = new ProductTypeGeneralController();
        specificController = new ProductTypeSpecificController();
//        validator = new ProductTypeValidator(mode, languageController, specificController);
    }

    public ProductType getProductType() {
        return productType;
    }

}
