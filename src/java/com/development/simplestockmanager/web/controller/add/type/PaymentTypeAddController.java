package com.development.simplestockmanager.web.controller.add.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.type.PaymentTypeCommonController;
import com.development.simplestockmanager.web.service.LanguageService;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for PaymentType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "paymentTypeAdd")
@ViewScoped
public class PaymentTypeAddController extends PaymentTypeCommonController implements AddController {

    public PaymentTypeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        paymentType = new PaymentType();
        translationEN_US = new PaymentTypeTranslation();
        translationES_ES = new PaymentTypeTranslation();
        translationCA_ES = new PaymentTypeTranslation();

        translationEN_US.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.EN_US));
        translationES_ES.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.ES_ES));
        translationCA_ES.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.CA_ES));
    }

    @Override
    public void add() {
        validator.setObject(paymentType);
        validator.setTranslationEN_US(translationEN_US);
        validator.setTranslationES_ES(translationES_ES);
        validator.setTranslationCA_ES(translationCA_ES);

        if (validator.validate()) {
            paymentType.setCreatedDate(new Date());
            paymentType.setLastModifiedDate(new Date());
            paymentType.setCreatedUser(user);
            paymentType.setLastModifiedUser(user);

            Long id = generalController.create(paymentType);
            boolean error = false;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                error = true;
            } else {
                translationEN_US.setReference(paymentType);
                translationES_ES.setReference(paymentType);
                translationCA_ES.setReference(paymentType);
                Long id_EN_US = translationGeneralController.create(translationEN_US);
                Long id_ES_ES = translationGeneralController.create(translationES_ES);
                Long id_CA_ES = translationGeneralController.create(translationCA_ES);

                if (id_EN_US == BusinessConstant.IDENTIFIER.INVALID
                        || id_ES_ES == BusinessConstant.IDENTIFIER.INVALID
                        || id_CA_ES == BusinessConstant.IDENTIFIER.INVALID) {
                    generalController.delete(paymentType);

                    if (id_EN_US != BusinessConstant.IDENTIFIER.INVALID) {
                        translationGeneralController.delete(translationEN_US);
                    }
                    if (id_ES_ES != BusinessConstant.IDENTIFIER.INVALID) {
                        translationGeneralController.delete(translationES_ES);
                    }
                    if (id_CA_ES != BusinessConstant.IDENTIFIER.INVALID) {
                        translationGeneralController.delete(translationCA_ES);
                    }

                    error = true;
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.TYPE.PAYMENT_TYPE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
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
