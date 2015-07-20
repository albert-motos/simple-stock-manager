package com.development.simplestockmanager.web.controller.search;

import com.development.simplestockmanager.business.object.nullpackage.StoreNull;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.StoreCommonController;
import com.development.simplestockmanager.web.controller.common.SearchController;
import com.development.simplestockmanager.web.object.selector.EmployeeSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Store object
 *
 * @author foxtrot
 */
@ManagedBean(name = "storeSearch")
@ViewScoped
public class StoreSearchController extends StoreCommonController implements SearchController {

    private Store browser;
    private List<Store> list;
    private long status;

    public StoreSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        employeeSelector = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        clear();
    }

    @Override
    public void search() {
        browser.setEmployee(employeeSelector.getSelectedValue());
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new StoreNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        employeeSelector.clear();
        
        super.clear();
    }
    
    public void initView(Store store) {
        this.store = store;
    }
    
    public void initEdit(Store store) {
        sendObjectToSession(WebConstant.SESSION.STORE, store);
        new NavigationService().redirect(WebConstant.WEB.EDIT.STORE);
    }

    public List<Store> getList() {
        return list;
    }

    public Store getBrowser() {
        return browser;
    }

    public void setBrowser(Store browser) {
        this.browser = browser;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
