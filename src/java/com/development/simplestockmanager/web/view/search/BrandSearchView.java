package com.development.simplestockmanager.web.view.search;

import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandSearch")
@ViewScoped
public class BrandSearchView extends BaseSearchView {

    private final BrandSpecificController specificController;

    private Brand browser;
    private Brand view;
    private List<Brand> list;

    public BrandSearchView() {
        specificController = new BrandSpecificController();

        clear();
    }

    @Override
    public void find() {
        list = specificController.findAllForBrowser(browser, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new Brand();
        view = new Brand();
        list = new ArrayList<>();
    }

    public void initView(Brand brand) {
        view = brand;
    }

    public void initEdit(Brand brand) {
        sendObjectToSession(WebConstant.SESSION.BRAND, brand);
        new NavigationService().redirect(WebConstant.WEB.EDIT.BRAND);
    }
    
    public List<Brand> getList() {
        return list;
    }
    
    public Brand getView() {
        return view;
    }

    public Brand getBrowser() {
        return browser;
    }

    public void setBrowser(Brand browser) {
        this.browser = browser;
    }

}
