package com.development.simplestockmanager.web.controller.edit.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.PaymentTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for PaymentType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "paymentTypeEdit")
@ViewScoped
public class PaymentTypeEditController extends PaymentTypeCommonController implements EditController {

    private PaymentType basePaymentType;
    private final PaymentTypeTranslation baseTranslationEN_US;
    private final PaymentTypeTranslation baseTranslationES_ES;
    private final PaymentTypeTranslation baseTranslationCA_ES;

    public PaymentTypeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            paymentType = (PaymentType) receiveObjectFromSession(WebConstant.SESSION.EMPLOYEE_TYPE);
            basePaymentType = new PaymentType(paymentType);
        } catch (Exception e) {
            back();
        }

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

        baseTranslationEN_US = new PaymentTypeTranslation(translationEN_US);
        baseTranslationES_ES = new PaymentTypeTranslation(translationES_ES);
        baseTranslationCA_ES = new PaymentTypeTranslation(translationCA_ES);
    }

    @Override
    public void edit() {
        if (paymentType.equals(basePaymentType)
                && translationEN_US.equals(baseTranslationEN_US)
                && translationES_ES.equals(baseTranslationES_ES)
                && translationCA_ES.equals(baseTranslationEN_US)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PAYMENT_TYPE) + paymentType.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(paymentType);
            validator.setTranslationEN_US(translationEN_US);
            validator.setTranslationES_ES(translationES_ES);
            validator.setTranslationCA_ES(translationCA_ES);

            if (validator.validate()) {
                paymentType.setLastModifiedDate(new Date());
                paymentType.setLastModifiedUser(user);

                Long status = generalController.update(paymentType);
                boolean error = false;

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    error = true;
                } else {
                    translationEN_US.setReference(paymentType);
                    translationES_ES.setReference(paymentType);
                    translationCA_ES.setReference(paymentType);
                    Long status_EN_US = translationGeneralController.update(translationEN_US);
                    Long status_ES_ES = translationGeneralController.update(translationES_ES);
                    Long status_CA_ES = translationGeneralController.update(translationCA_ES);

                    if (status_EN_US == BusinessConstant.UPDATE.FAILURE
                            || status_ES_ES == BusinessConstant.UPDATE.FAILURE
                            || status_CA_ES == BusinessConstant.UPDATE.FAILURE) {
                        generalController.update(basePaymentType);
                        translationGeneralController.update(baseTranslationEN_US);
                        translationGeneralController.update(baseTranslationES_ES);
                        translationGeneralController.update(baseTranslationCA_ES);

                        error = true;
                    } else {
                        action = true;
                        severity = FacesMessage.SEVERITY_INFO;
                        summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                        detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PAYMENT_TYPE) + paymentType.getId()
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.TYPE.PAYMENT_TYPE);
    }

}
