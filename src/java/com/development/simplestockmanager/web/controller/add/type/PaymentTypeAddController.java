package com.development.simplestockmanager.web.controller.add.type;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.type.PaymentTypeCommonController;
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
                    translationGeneralController.delete(translationEN_US);
                    translationGeneralController.delete(translationES_ES);
                    translationGeneralController.delete(translationCA_ES);
                    
                    error = true;
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PAYMENT_TYPE) + id
                            + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
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
