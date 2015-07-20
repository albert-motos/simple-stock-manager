package com.development.simplestockmanager.web.controller.edit.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.ProductTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for ProductType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productTypeEdit")
@ViewScoped
public class ProductTypeEditController extends ProductTypeCommonController implements EditController {

    private ProductType baseProductType;
    private final ProductTypeTranslation baseTranslationEN_US;
    private final ProductTypeTranslation baseTranslationES_ES;
    private final ProductTypeTranslation baseTranslationCA_ES;

    public ProductTypeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            productType = (ProductType) receiveObjectFromSession(WebConstant.SESSION.PRODUCT_TYPE);
            baseProductType = new ProductType(productType);
        } catch (Exception e) {
            back();
        }

        for (ProductTypeTranslation productTypeTranslation : productType.getProductTypeTranslationList()) {
            if (productTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.EN_US)) {
                translationEN_US = productTypeTranslation;
            }

            if (productTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.ES_ES)) {
                translationES_ES = productTypeTranslation;
            }

            if (productTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.CA_ES)) {
                translationCA_ES = productTypeTranslation;
            }
        }

        baseTranslationEN_US = new ProductTypeTranslation(translationEN_US);
        baseTranslationES_ES = new ProductTypeTranslation(translationES_ES);
        baseTranslationCA_ES = new ProductTypeTranslation(translationCA_ES);
    }

    @Override
    public void edit() {
        if (productType.equals(baseProductType)
                && translationEN_US.equals(baseTranslationEN_US)
                && translationES_ES.equals(baseTranslationES_ES)
                && translationCA_ES.equals(baseTranslationEN_US)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT_TYPE) + productType.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(productType);
            validator.setTranslationEN_US(translationEN_US);
            validator.setTranslationES_ES(translationES_ES);
            validator.setTranslationCA_ES(translationCA_ES);

            if (validator.validate()) {
                productType.setLastModifiedDate(new Date());
                productType.setLastModifiedUser(user);

                Long status = generalController.update(productType);
                boolean error = false;

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    error = true;
                } else {
                    translationEN_US.setReference(productType);
                    translationES_ES.setReference(productType);
                    translationCA_ES.setReference(productType);
                    Long status_EN_US = translationGeneralController.update(translationEN_US);
                    Long status_ES_ES = translationGeneralController.update(translationES_ES);
                    Long status_CA_ES = translationGeneralController.update(translationCA_ES);

                    if (status_EN_US == BusinessConstant.UPDATE.FAILURE
                            || status_ES_ES == BusinessConstant.UPDATE.FAILURE
                            || status_CA_ES == BusinessConstant.UPDATE.FAILURE) {
                        generalController.update(baseProductType);
                        translationGeneralController.update(baseTranslationEN_US);
                        translationGeneralController.update(baseTranslationES_ES);
                        translationGeneralController.update(baseTranslationCA_ES);

                        error = true;
                    } else {
                        action = true;
                        severity = FacesMessage.SEVERITY_INFO;
                        summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                        detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT_TYPE) + productType.getId()
                                + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.EDIT);
                    }
                }

                if (error) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }

    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.TYPE.PRODUCT_TYPE);
    }

}
