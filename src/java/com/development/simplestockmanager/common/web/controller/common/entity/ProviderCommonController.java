package com.development.simplestockmanager.common.web.controller.common.entity;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.ProviderGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ProviderSpecificController;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.web.object.validator.entity.ProviderValidator;

/**
 * Common controller class for Provider object
 *
 * @author foxtrot
 */
public class ProviderCommonController extends BaseCommonController {
    
    protected final ProviderValidator validator;
    protected final ProviderGeneralController generalController;
    protected final ProviderSpecificController specificController;
    
    protected Provider provider;

    public ProviderCommonController(long mode) {
        generalController = new ProviderGeneralController();
        specificController = new ProviderSpecificController();
        validator = new ProviderValidator(mode, languageController);
    }

    public Provider getProvider() {
        return provider;
    }

}
