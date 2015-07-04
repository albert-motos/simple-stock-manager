package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import java.io.Serializable;

/**
 * Base class for add view controller
 *
 * @author foxtrot
 */
abstract class BaseAddView implements Serializable {

    protected InternationalizationController internationalizationController;
    protected boolean added;
    protected Employee user;

    public BaseAddView() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new InternationalizationController(user.getLanguageType().getCode());
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
        new NavigationService().redirect(Constant.URL.INDEX);
    }

}
