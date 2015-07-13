package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.BrandGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.web.object.validator.BrandValidator;

/**
 * Common controller class for Brand object
 *
 * @author foxtrot
 */
public class BrandCommonController extends BaseCommonController {

    protected final BrandValidator validator;
    protected final BrandGeneralController generalController;
    protected final BrandSpecificController specificController;

    protected Brand brand;

    public BrandCommonController(long mode) {
        generalController = new BrandGeneralController();
        specificController = new BrandSpecificController();
        validator = new BrandValidator(mode, languageController, specificController);
    }

    public Brand getBrand() {
        return brand;
    }

}
