package com.development.simplestockmanager.web.controller.search.entity;

import com.development.simplestockmanager.business.object.nullpackage.ProductNull;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.ProductCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import com.development.simplestockmanager.web.object.selector.entity.BrandSelector;
import com.development.simplestockmanager.web.object.selector.entity.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Product object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productSearch")
@ViewScoped
public class ProductSearchController extends ProductCommonController implements SearchController {

    private Product browser;
    private List<Product> list;

    public ProductSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        productTypeSelector = new ProductTypeSelector(WebConstant.SELECTOR.MODE.ALL);
        brandSelector = new BrandSelector(WebConstant.SELECTOR.MODE.ALL);
        providerSelector = new ProviderSelector(WebConstant.SELECTOR.MODE.ALL);
        clear();
    }

    @Override
    public void search() {
        browser.setProductType(productTypeSelector.getSelectedValue());
        browser.setBrand(brandSelector.getSelectedValue());
        browser.setProvider(providerSelector.getSelectedValue());
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new ProductNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        productTypeSelector.clear();
        brandSelector.clear();
        providerSelector.clear();
        
        super.clear();
    }
    
    public void initView(Product product) {
        this.product = product;
    }
    
    public void initEdit(Product product) {
        sendObjectToSession(WebConstant.SESSION.PRODUCT, product);
        new NavigationService().redirect(WebConstant.WEB.EDIT.ENTITY.PRODUCT);
    }

    public List<Product> getList() {
        return list;
    }

    public Product getBrowser() {
        return browser;
    }

    public void setBrowser(Product browser) {
        this.browser = browser;
    }

}
