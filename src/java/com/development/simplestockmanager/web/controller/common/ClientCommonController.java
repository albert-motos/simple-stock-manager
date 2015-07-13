package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ClientSpecificController;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.ClientValidator;

/**
 * Common controller class for Brand object
 *
 * @author foxtrot
 */
public class ClientCommonController extends BaseCommonController {
    
    protected final ClientValidator validator;
    protected final ClientGeneralController generalController;
    protected final ClientSpecificController specificController;
    
    protected Client client;
    protected SexTypeSelector sexTypeSelector;
    
    public ClientCommonController(long mode) {
        generalController = new ClientGeneralController();
        specificController = new ClientSpecificController();
        validator = new ClientValidator(mode, languageController);
    }

    public Client getClient() {
        return client;
    }

    public SexTypeSelector getSexTypeSelector() {
        return sexTypeSelector;
    }
}
