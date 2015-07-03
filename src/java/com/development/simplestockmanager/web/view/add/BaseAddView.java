package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.service.general.AuthenticationService;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 * Base class for add view controller
 *
 * @author foxtrot
 */
@ManagedBean
abstract class BaseAddView implements Serializable {

    protected boolean added;
    protected Employee user;

    public BaseAddView() {
        user = new AuthenticationService().getCurrentEmployee();
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
