package com.development.simplestockmanager.common.web.controller.common.type;

import com.development.simplestockmanager.business.object.controller.general.SexTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.SexTypeTranslationGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.SexTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.web.object.validator.type.SexTypeValidator;

/**
 * Common controller class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeCommonController extends BaseCommonController {

    protected final SexTypeValidator validator;
    protected final SexTypeGeneralController generalController;
    protected final SexTypeSpecificController specificController;
    protected final SexTypeTranslationGeneralController translationGeneralController;
    protected final SexTypeTranslationSpecificController translationSpecificController;

    protected SexType sexType;
    protected SexTypeTranslation translationEN_US;
    protected SexTypeTranslation translationES_ES;
    protected SexTypeTranslation translationCA_ES;

    public SexTypeCommonController(long mode) {
        generalController = new SexTypeGeneralController();
        specificController = new SexTypeSpecificController();
        translationGeneralController = new SexTypeTranslationGeneralController();
        translationSpecificController = new SexTypeTranslationSpecificController();
        validator = new SexTypeValidator(mode, languageController, specificController);
    }

    public SexType getSexType() {
        return sexType;
    }

    public SexTypeTranslation getTranslationEN_US() {
        return translationEN_US;
    }

    public SexTypeTranslation getTranslationES_ES() {
        return translationES_ES;
    }

    public SexTypeTranslation getTranslationCA_ES() {
        return translationCA_ES;
    }

}
