package com.development.simplestockmanager.web.controller.search.type;

import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.EmployeeTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for EmployeeType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeTypeSearch")
@ViewScoped
public class EmployeeTypeSearchController extends EmployeeTypeCommonController implements SearchController {

    private EmployeeType browser;
    private EmployeeTypeTranslation translation;
    private List<EmployeeType> list;
    private long status;

    public EmployeeTypeSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        clear();
    }

    @Override
    public void search() {
        list = specificController.findAllForBrowser(browser, translation, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new EmployeeTypeNull();
        translation = new EmployeeTypeTranslationNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public void initEdit(EmployeeType employeeType) {
        sendObjectToSession(WebConstant.SESSION.EMPLOYEE_TYPE, employeeType);
        new NavigationService().redirect(WebConstant.WEB.EDIT.TYPE.EMPLOYEE_TYPE);
    }
    
    public List<EmployeeType> getList() {
        return list;
    }

    public EmployeeType getBrowser() {
        return browser;
    }

    public void setBrowser(EmployeeType browser) {
        this.browser = browser;
    }

    public EmployeeTypeTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(EmployeeTypeTranslation translation) {
        this.translation = translation;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
