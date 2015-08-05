package com.development.simplestockmanager.web.controller.add.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.type.SexTypeCommonController;
import com.development.simplestockmanager.web.service.LanguageService;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for SexType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "sexTypeAdd")
@ViewScoped
public class SexTypeAddController extends SexTypeCommonController implements AddController {

    public SexTypeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        sexType = new SexType();
        translationEN_US = new SexTypeTranslation();
        translationES_ES = new SexTypeTranslation();
        translationCA_ES = new SexTypeTranslation();

        translationEN_US.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.EN_US));
        translationES_ES.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.ES_ES));
        translationCA_ES.setLanguage(LanguageService.getLanguage(CommonConstant.LANGUAGE.CA_ES));
    }

    @Override
    public void add() {
        validator.setObject(sexType);
        validator.setTranslationEN_US(translationEN_US);
        validator.setTranslationES_ES(translationES_ES);
        validator.setTranslationCA_ES(translationCA_ES);

        if (validator.validate()) {
            sexType.setCreatedDate(new Date());
            sexType.setLastModifiedDate(new Date());
            sexType.setCreatedUser(user);
            sexType.setLastModifiedUser(user);

            Long id = generalController.create(sexType);
            boolean error = false;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                error = true;
            } else {
                translationEN_US.setReference(sexType);
                translationES_ES.setReference(sexType);
                translationCA_ES.setReference(sexType);
                Long id_EN_US = translationGeneralController.create(translationEN_US);
                Long id_ES_ES = translationGeneralController.create(translationES_ES);
                Long id_CA_ES = translationGeneralController.create(translationCA_ES);

                if (id_EN_US == BusinessConstant.IDENTIFIER.INVALID
                        || id_ES_ES == BusinessConstant.IDENTIFIER.INVALID
                        || id_CA_ES == BusinessConstant.IDENTIFIER.INVALID) {
                    generalController.delete(sexType);

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
                    detail = messageService.getDetail(CommonConstant.TYPE.SEX_TYPE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
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
