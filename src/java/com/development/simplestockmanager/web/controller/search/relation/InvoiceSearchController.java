package com.development.simplestockmanager.web.controller.search.relation;

import com.development.simplestockmanager.business.object.nullpackage.InvoiceNull;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.relation.InvoiceCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.entity.ClientSelector;
import com.development.simplestockmanager.web.object.selector.entity.EmployeeSelector;
import com.development.simplestockmanager.web.object.selector.type.PaymentTypeSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Invoice object
 *
 * @author foxtrot
 */
@ManagedBean(name = "invoiceSearch")
@ViewScoped
public class InvoiceSearchController extends InvoiceCommonController implements SearchController {

    private Invoice browser;
    private List<Invoice> list;
    private EmployeeSelector employeeSelector;

    public InvoiceSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        clientSelector = new ClientSelector(WebConstant.SELECTOR.MODE.ALL);
        paymentTypeSelector = new PaymentTypeSelector(WebConstant.SELECTOR.MODE.ALL);
        employeeSelector = new EmployeeSelector(WebConstant.SELECTOR.MODE.ALL);
        clear();
    }

    @Override
    public void search() {
        browser.setClient(clientSelector.getSelectedValue());
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new InvoiceNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        clientSelector.clear();
        
        super.clear();
    }

    public void initView(Invoice invoice) {
        this.invoice = invoice;
    }
    
    public List<Invoice> getList() {
        return list;
    }

    public Invoice getBrowser() {
        return browser;
    }

    public void setBrowser(Invoice browser) {
        this.browser = browser;
    }

    public EmployeeSelector getEmployeeSelector() {
        return employeeSelector;
    }

}
