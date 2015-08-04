package com.development.simplestockmanager.common.web.controller.common.type;

import com.development.simplestockmanager.business.object.controller.general.ProductTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.ProductTypeTranslationGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ProductTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.ProductTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.web.object.validator.type.ProductTypeValidator;

/**
 * Common controller class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeCommonController extends BaseCommonController {

    protected final ProductTypeValidator validator;
    protected final ProductTypeGeneralController generalController;
    protected final ProductTypeSpecificController specificController;
    protected final ProductTypeTranslationGeneralController translationGeneralController;
    protected final ProductTypeTranslationSpecificController translationSpecificController;

    protected ProductType productType;
    protected ProductTypeTranslation translationEN_US;
    protected ProductTypeTranslation translationES_ES;
    protected ProductTypeTranslation translationCA_ES;

    public ProductTypeCommonController(long mode) {
        generalController = new ProductTypeGeneralController();
        specificController = new ProductTypeSpecificController();
        translationGeneralController = new ProductTypeTranslationGeneralController();
        translationSpecificController = new ProductTypeTranslationSpecificController();
        validator = new ProductTypeValidator(mode, specificController);
    }

    public ProductType getProductType() {
        return productType;
    }

    public ProductTypeTranslation getTranslationEN_US() {
        return translationEN_US;
    }

    public ProductTypeTranslation getTranslationES_ES() {
        return translationES_ES;
    }

    public ProductTypeTranslation getTranslationCA_ES() {
        return translationCA_ES;
    }

}
