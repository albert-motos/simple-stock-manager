package com.development.simplestockmanager.common.web.controller.common.type;

import com.development.simplestockmanager.business.object.controller.general.PriceTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.PriceTypeTranslationGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.PriceTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
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
    protected final PriceTypeTranslationGeneralController translationGeneralController;
    protected final PriceTypeTranslationSpecificController translationSpecificController;

    protected PriceType priceType;
    protected PriceTypeTranslation translationEN_US;
    protected PriceTypeTranslation translationES_ES;
    protected PriceTypeTranslation translationCA_ES;

    public PriceTypeCommonController(long mode) {
        generalController = new PriceTypeGeneralController();
        specificController = new PriceTypeSpecificController();
        translationGeneralController = new PriceTypeTranslationGeneralController();
        translationSpecificController = new PriceTypeTranslationSpecificController();
        validator = new PriceTypeValidator(mode, specificController);
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public PriceTypeTranslation getTranslationEN_US() {
        return translationEN_US;
    }

    public PriceTypeTranslation getTranslationES_ES() {
        return translationES_ES;
    }

    public PriceTypeTranslation getTranslationCA_ES() {
        return translationCA_ES;
    }

}
