package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.common.BusinessConstant;
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
public class BaseCommonController implements Serializable {

    protected InternationalizationController internationalizationController;
    protected Employee user;

    public BaseCommonController() {
        user = new AuthenticationService().getCurrentEmployee();
        internationalizationController = new InternationalizationController(user.getLanguageType().getCode());
    }
    
    public void back() {
        new NavigationService().redirect(BusinessConstant.URL.INDEX);
    }

}
