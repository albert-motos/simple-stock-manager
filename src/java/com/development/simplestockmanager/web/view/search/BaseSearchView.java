package com.development.simplestockmanager.web.view.search;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import java.io.Serializable;

/**
 * Base class for search view controller
 *
 * @author foxtrot
 */
abstract class BaseSearchView implements Serializable {

    protected InternationalizationController internationalizationController;
    protected Employee user;

    public BaseSearchView() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new InternationalizationController(user.getLanguageType().getCode());
    }

    /**
     * Main function of find view for update list with browser fields
     */
    abstract public void find();

    /**
     * Main function of find view for clear browser
     */
    abstract public void clear();
}
