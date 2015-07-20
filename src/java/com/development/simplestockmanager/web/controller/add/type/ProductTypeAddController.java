package com.development.simplestockmanager.web.controller.add.type;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.type.ProductTypeCommonController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for ProductType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productTypeAdd")
@ViewScoped
public class ProductTypeAddController extends ProductTypeCommonController implements AddController {

    public ProductTypeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        productType = new ProductType();
        translationEN_US = new ProductTypeTranslation();
        translationES_ES = new ProductTypeTranslation();
        translationCA_ES = new ProductTypeTranslation();
    }

    @Override
    public void add() {
        validator.setObject(productType);
        validator.setTranslationEN_US(translationEN_US);
        validator.setTranslationES_ES(translationES_ES);
        validator.setTranslationCA_ES(translationCA_ES);

        if (validator.validate()) {
            productType.setCreatedDate(new Date());
            productType.setLastModifiedDate(new Date());
            productType.setCreatedUser(user);
            productType.setLastModifiedUser(user);

            Long id = generalController.create(productType);
            boolean error = false;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                error = true;
            } else {
                translationEN_US.setReference(productType);
                translationES_ES.setReference(productType);
                translationCA_ES.setReference(productType);
                Long id_EN_US = translationGeneralController.create(translationEN_US);
                Long id_ES_ES = translationGeneralController.create(translationES_ES);
                Long id_CA_ES = translationGeneralController.create(translationCA_ES);

                if (id_EN_US == BusinessConstant.IDENTIFIER.INVALID
                        || id_ES_ES == BusinessConstant.IDENTIFIER.INVALID
                        || id_CA_ES == BusinessConstant.IDENTIFIER.INVALID) {
                    generalController.delete(productType);
                    translationGeneralController.delete(translationEN_US);
                    translationGeneralController.delete(translationES_ES);
                    translationGeneralController.delete(translationCA_ES);
                    
                    error = true;
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT_TYPE) + id
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
