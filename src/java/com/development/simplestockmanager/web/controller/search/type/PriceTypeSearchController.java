package com.development.simplestockmanager.web.controller.search.type;

import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.PriceTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for PriceType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "priceTypeSearch")
@ViewScoped
public class PriceTypeSearchController extends PriceTypeCommonController implements SearchController {

    private PriceType browser;
    private PriceTypeTranslation translation;
    private List<PriceType> list;

    public PriceTypeSearchController() {
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
        browser = new PriceTypeNull();
        translation = new PriceTypeTranslationNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(PriceType priceType) {
        this.priceType = priceType;
    }

    public void initEdit(PriceType priceType) {
        sendObjectToSession(WebConstant.SESSION.PRICE_TYPE, priceType);
        new NavigationService().redirect(WebConstant.WEB.EDIT.TYPE.PRICE_TYPE);
    }
    
    public List<PriceType> getList() {
        return list;
    }

    public PriceType getBrowser() {
        return browser;
    }

    public void setBrowser(PriceType browser) {
        this.browser = browser;
    }

    public PriceTypeTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(PriceTypeTranslation translation) {
        this.translation = translation;
    }

}
