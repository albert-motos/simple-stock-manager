package com.development.simplestockmanager.web.controller.add.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.type.PriceTypeCommonController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for PriceType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "priceTypeAdd")
@ViewScoped
public class PriceTypeAddController extends PriceTypeCommonController implements AddController {

    public PriceTypeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        priceType = new PriceType();
        translationEN_US = new PriceTypeTranslation();
        translationES_ES = new PriceTypeTranslation();
        translationCA_ES = new PriceTypeTranslation();
    }

    @Override
    public void add() {
        validator.setObject(priceType);
        validator.setTranslationEN_US(translationEN_US);
        validator.setTranslationES_ES(translationES_ES);
        validator.setTranslationCA_ES(translationCA_ES);

        if (validator.validate()) {
            priceType.setCreatedDate(new Date());
            priceType.setLastModifiedDate(new Date());
            priceType.setCreatedUser(user);
            priceType.setLastModifiedUser(user);

            Long id = generalController.create(priceType);
            boolean error = false;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                error = true;
            } else {
                translationEN_US.setReference(priceType);
                translationES_ES.setReference(priceType);
                translationCA_ES.setReference(priceType);
                Long id_EN_US = translationGeneralController.create(translationEN_US);
                Long id_ES_ES = translationGeneralController.create(translationES_ES);
                Long id_CA_ES = translationGeneralController.create(translationCA_ES);

                if (id_EN_US == BusinessConstant.IDENTIFIER.INVALID
                        || id_ES_ES == BusinessConstant.IDENTIFIER.INVALID
                        || id_CA_ES == BusinessConstant.IDENTIFIER.INVALID) {
                    generalController.delete(priceType);
                    translationGeneralController.delete(translationEN_US);
                    translationGeneralController.delete(translationES_ES);
                    translationGeneralController.delete(translationCA_ES);
                    
                    error = true;
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRICE_TYPE) + id
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
