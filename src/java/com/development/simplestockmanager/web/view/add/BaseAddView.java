package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.Authentication;
import com.development.simplestockmanager.web.common.NavigationView;
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
        user = new Authentication().getCurrentUser();
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
        new NavigationView().redirect(Constant.URL.INDEX);
    }

}
