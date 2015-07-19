package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.PriceTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeSpecificController;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.web.object.validator.type.PriceTypeValidator;

/**
 * Common controller class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeCommonController extends BaseCommonController {

    protected final PriceTypeValidator validator;
    protected final PriceTypeGeneralController generalController;
    protected final PriceTypeSpecificController specificController;

    protected PriceType priceType;

    public PriceTypeCommonController(long mode) {
        generalController = new PriceTypeGeneralController();
        specificController = new PriceTypeSpecificController();
        validator = new PriceTypeValidator(mode, languageController, specificController);
    }

    public PriceType getPriceType() {
        return priceType;
    }

}
