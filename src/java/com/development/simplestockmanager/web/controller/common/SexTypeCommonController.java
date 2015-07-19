package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.SexTypeGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.SexTypeSpecificController;
import com.development.simplestockmanager.business.persistence.SexType;
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

    protected SexType sexType;

    public SexTypeCommonController(long mode) {
        generalController = new SexTypeGeneralController();
        specificController = new SexTypeSpecificController();
        validator = new SexTypeValidator(mode, languageController, specificController);
    }

    public SexType getSexType() {
        return sexType;
    }

}
