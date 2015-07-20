package com.development.simplestockmanager.web.controller.search;

import com.development.simplestockmanager.business.object.nullpackage.ClientNull;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.ClientCommonController;
import com.development.simplestockmanager.web.controller.common.SearchController;
import com.development.simplestockmanager.web.object.selector.EmployeeSelector;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Client object
 *
 * @author foxtrot
 */
@ManagedBean(name = "clientSearch")
@ViewScoped
public class ClientSearchController extends ClientCommonController implements SearchController {

    private Client browser;
    private List<Client> list;
    private long status;

    public ClientSearchController() {
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
        browser = new ClientNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }
    
    public void initView(Client client) {
        this.client = client;
    }
    
    public void initEdit(Client client) {
        sendObjectToSession(WebConstant.SESSION.CLIENT, client);
        new NavigationService().redirect(WebConstant.WEB.EDIT.CLIENT);
    }

    public List<Client> getList() {
        return list;
    }

    public Client getBrowser() {
        return browser;
    }

    public void setBrowser(Client browser) {
        this.browser = browser;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
