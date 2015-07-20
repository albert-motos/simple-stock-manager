package com.development.simplestockmanager.common.web.controller.common.entity;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.StoreGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.StoreSpecificController;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.web.object.selector.entity.EmployeeSelector;
import com.development.simplestockmanager.web.object.validator.entity.StoreValidator;

/**
 * Common controller class for Store object
 *
 * @author foxtrot
 */
public class StoreCommonController extends BaseCommonController {
    
    protected final StoreValidator validator;
    protected final StoreGeneralController generalController;
    protected final StoreSpecificController specificController;
    
    protected EmployeeSelector employeeSelector;
    
    protected Store store;

    public StoreCommonController(long mode) {
        generalController = new StoreGeneralController();
        specificController = new StoreSpecificController();
        validator = new StoreValidator(mode, languageController);
    }

    public Store getStore() {
        return store;
    }

    public EmployeeSelector getEmployeeSelector() {
        return employeeSelector;
    }

}
