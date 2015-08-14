package com.development.simplestockmanager.web.controller.edit.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.PriceTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for PriceType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "priceTypeEdit")
@ViewScoped
public class PriceTypeEditController extends PriceTypeCommonController implements EditController {

    private PriceType basePriceType;
    private final PriceTypeTranslation baseTranslationEN_US;
    private final PriceTypeTranslation baseTranslationES_ES;
    private final PriceTypeTranslation baseTranslationCA_ES;

    public PriceTypeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            priceType = (PriceType) receiveObjectFromSession(WebConstant.SESSION.PRICE_TYPE);
            basePriceType = new PriceType(priceType);
        } catch (Exception e) {
            back();
        }

        for (PriceTypeTranslation priceTypeTranslation : priceType.getPriceTypeTranslationList()) {
            if (priceTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.EN_US)) {
                translationEN_US = priceTypeTranslation;
            }

            if (priceTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.ES_ES)) {
                translationES_ES = priceTypeTranslation;
            }

            if (priceTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.CA_ES)) {
                translationCA_ES = priceTypeTranslation;
            }
        }

        baseTranslationEN_US = new PriceTypeTranslation(translationEN_US);
        baseTranslationES_ES = new PriceTypeTranslation(translationES_ES);
        baseTranslationCA_ES = new PriceTypeTranslation(translationCA_ES);
    }

    @Override
    public void edit() {
        if (priceType.equals(basePriceType)
                && translationEN_US.equals(baseTranslationEN_US)
                && translationES_ES.equals(baseTranslationES_ES)
                && translationCA_ES.equals(baseTranslationEN_US)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.TYPE.PRICE_TYPE, priceType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(priceType);
            validator.setTranslationEN_US(translationEN_US);
            validator.setTranslationES_ES(translationES_ES);
            validator.setTranslationCA_ES(translationCA_ES);

            if (validator.validate()) {
                priceType.setLastModifiedDate(new Date());
                priceType.setLastModifiedUser(user);

                Long feedback = generalController.update(priceType);
                boolean error = false;

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    error = true;
                } else {
                    translationEN_US.setReference(priceType);
                    translationES_ES.setReference(priceType);
                    translationCA_ES.setReference(priceType);
                    Long status_EN_US = translationGeneralController.update(translationEN_US);
                    Long status_ES_ES = translationGeneralController.update(translationES_ES);
                    Long status_CA_ES = translationGeneralController.update(translationCA_ES);

                    if (status_EN_US == BusinessConstant.UPDATE.FAILURE
                            || status_ES_ES == BusinessConstant.UPDATE.FAILURE
                            || status_CA_ES == BusinessConstant.UPDATE.FAILURE) {
                        generalController.update(basePriceType);

                        if (status_EN_US != BusinessConstant.IDENTIFIER.INVALID) {
                            translationGeneralController.update(baseTranslationEN_US);
                        }
                        if (status_ES_ES != BusinessConstant.IDENTIFIER.INVALID) {
                            translationGeneralController.update(baseTranslationES_ES);
                        }
                        if (status_CA_ES != BusinessConstant.IDENTIFIER.INVALID) {
                            translationGeneralController.update(baseTranslationCA_ES);
                        }

                        error = true;
                    } else {
                        action = true;
                        severity = FacesMessage.SEVERITY_INFO;
                        summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                        detail = messageService.getDetail(CommonConstant.TYPE.PRICE_TYPE, priceType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                    }
                }

                if (error) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.TYPE.PRICE_TYPE);
    }

}
