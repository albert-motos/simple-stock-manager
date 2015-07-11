package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import java.io.Serializable;

/**
 * Base class for add view controller
 *
 * @author foxtrot
 */
abstract class BaseAddView implements Serializable {

    protected LanguageController internationalizationController;
    protected boolean added;
    protected Employee user;

    public BaseAddView() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new LanguageController(user.getLanguageType().getCode());
        added = false;
    }

    /**
     * Main function of add view
     */
    abstract public void add();

    public boolean isAdded() {
        return added;
    }
    
    public void back() {
        new NavigationService().redirect(BusinessConstant.URL.INDEX);
    }

}
