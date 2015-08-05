package com.development.simplestockmanager.web.controller.search.type;

import com.development.simplestockmanager.business.object.nullpackage.ProductTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.ProductTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for ProductType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productTypeSearch")
@ViewScoped
public class ProductTypeSearchController extends ProductTypeCommonController implements SearchController {

    private ProductType browser;
    private ProductTypeTranslation translation;
    private List<ProductType> list;

    public ProductTypeSearchController() {
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
        browser = new ProductTypeNull();
        translation = new ProductTypeTranslationNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(ProductType productType) {
        this.productType = productType;
    }

    public void initEdit(ProductType productType) {
        sendObjectToSession(WebConstant.SESSION.PRODUCT_TYPE, productType);
        new NavigationService().redirect(WebConstant.WEB.EDIT.TYPE.PRODUCT_TYPE);
    }
    
    public List<ProductType> getList() {
        return list;
    }

    public ProductType getBrowser() {
        return browser;
    }

    public void setBrowser(ProductType browser) {
        this.browser = browser;
    }

    public ProductTypeTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(ProductTypeTranslation translation) {
        this.translation = translation;
    }

}
