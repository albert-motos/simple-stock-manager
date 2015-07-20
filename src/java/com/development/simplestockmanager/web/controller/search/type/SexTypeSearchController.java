package com.development.simplestockmanager.web.controller.search.type;

import com.development.simplestockmanager.business.object.nullpackage.SexTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.SexTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.type.SexTypeCommonController;
import com.development.simplestockmanager.web.controller.common.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for SexType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "sexTypeSearch")
@ViewScoped
public class SexTypeSearchController extends SexTypeCommonController implements SearchController {

    private SexType browser;
    private SexTypeTranslation translation;
    private List<SexType> list;
    private long status;

    public SexTypeSearchController() {
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
        browser = new SexTypeNull();
        translation = new SexTypeTranslationNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(SexType sexType) {
        this.sexType = sexType;
    }

    public void initEdit(SexType sexType) {
        sendObjectToSession(WebConstant.SESSION.SEX_TYPE, sexType);
        new NavigationService().redirect(WebConstant.WEB.EDIT.SEX_TYPE);
    }
    
    public List<SexType> getList() {
        return list;
    }

    public SexType getBrowser() {
        return browser;
    }

    public void setBrowser(SexType browser) {
        this.browser = browser;
    }

    public SexTypeTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(SexTypeTranslation translation) {
        this.translation = translation;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
