package com.development.simplestockmanager.web.controller.search.entity;

import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.EmployeeCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.selector.type.LanguageSelector;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
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

    public EmployeeSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ALL);
        employeeTypeSelector = new EmployeeTypeSelector(WebConstant.SELECTOR.MODE.ALL);
        languageSelector = new LanguageSelector(WebConstant.SELECTOR.MODE.ALL);
        clear();
    }

    @Override
    public void search() {
        browser.setSexType(sexTypeSelector.getSelectedValue());
        browser.setEmployeeType(employeeTypeSelector.getSelectedValue());
        browser.setLanguage(languageSelector.getSelectedValue());
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new EmployeeNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        sexTypeSelector.clear();
        employeeTypeSelector.clear();
        languageSelector.clear();
        
        super.clear();
    }
    
    public void initView(Employee employee) {
        this.employee = employee;
    }
    
    public void initEdit(Employee employee) {
        sendObjectToSession(WebConstant.SESSION.EMPLOYEE, employee);
        new NavigationService().redirect(WebConstant.WEB.EDIT.ENTITY.EMPLOYEE);
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

}
