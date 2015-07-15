package com.development.simplestockmanager.web.controller.search;

import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.EmployeeCommonController;
import com.development.simplestockmanager.web.controller.common.SearchController;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Employee object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeSearch")
@ViewScoped
public class EmployeeSearchController extends EmployeeCommonController implements SearchController {

    private Employee browser;
    private List<Employee> list;
    private long status;

    public EmployeeSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ALL, languageController.getLanguage());
        clear();
    }

    @Override
    public void search() {
        browser.setSexType(sexTypeSelector.getSelectedValue());
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new EmployeeNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
    }
    
    public void initView(Employee employee) {
        employee.setSexType(sexTypeSelector.getSelectedValue());
        this.employee = employee;
    }
    
    public void initEdit(Employee employee) {
        sendObjectToSession(WebConstant.SESSION.CLIENT, employee);
        new NavigationService().redirect(WebConstant.WEB.EDIT.CLIENT);
    }

    public List<Employee> getList() {
        return list;
    }

    public Employee getBrowser() {
        return browser;
    }

    public void setBrowser(Employee browser) {
        this.browser = browser;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
