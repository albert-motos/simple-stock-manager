package com.development.simplestockmanager.web.controller.search.type;

import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.PaymentTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for PaymentType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "paymentTypeSearch")
@ViewScoped
public class PaymentTypeSearchController extends PaymentTypeCommonController implements SearchController {

    private PaymentType browser;
    private PaymentTypeTranslation translation;
    private List<PaymentType> list;

    public PaymentTypeSearchController() {
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
        browser = new PaymentTypeNull();
        translation = new PaymentTypeTranslationNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(PaymentType paymentType) {
        this.paymentType = paymentType;
        for (PaymentTypeTranslation paymentTypeTranslation : paymentType.getPaymentTypeTranslationList()) {
            if (paymentTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.EN_US)) {
                translationEN_US = paymentTypeTranslation;
            }

            if (paymentTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.ES_ES)) {
                translationES_ES = paymentTypeTranslation;
            }

            if (paymentTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.CA_ES)) {
                translationCA_ES = paymentTypeTranslation;
            }
        }
    }

    public void initEdit(PaymentType paymentType) {
        sendObjectToSession(WebConstant.SESSION.PAYMENT_TYPE, paymentType);
        new NavigationService().redirect(WebConstant.WEB.EDIT.TYPE.PAYMENT_TYPE);
    }
    
    public List<PaymentType> getList() {
        return list;
    }

    public PaymentType getBrowser() {
        return browser;
    }

    public void setBrowser(PaymentType browser) {
        this.browser = browser;
    }

    public PaymentTypeTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(PaymentTypeTranslation translation) {
        this.translation = translation;
    }

}
