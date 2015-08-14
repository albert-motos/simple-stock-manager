package com.development.simplestockmanager.web.controller.edit.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.SexTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for SexType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "sexTypeEdit")
@ViewScoped
public class SexTypeEditController extends SexTypeCommonController implements EditController {

    private SexType baseSexType;
    private final SexTypeTranslation baseTranslationEN_US;
    private final SexTypeTranslation baseTranslationES_ES;
    private final SexTypeTranslation baseTranslationCA_ES;

    public SexTypeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            sexType = (SexType) receiveObjectFromSession(WebConstant.SESSION.SEX_TYPE);
            baseSexType = new SexType(sexType);
        } catch (Exception e) {
            back();
        }

        for (SexTypeTranslation sexTypeTranslation : sexType.getSexTypeTranslationList()) {
            if (sexTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.EN_US)) {
                translationEN_US = sexTypeTranslation;
            }

            if (sexTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.ES_ES)) {
                translationES_ES = sexTypeTranslation;
            }

            if (sexTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.CA_ES)) {
                translationCA_ES = sexTypeTranslation;
            }
        }

        baseTranslationEN_US = new SexTypeTranslation(translationEN_US);
        baseTranslationES_ES = new SexTypeTranslation(translationES_ES);
        baseTranslationCA_ES = new SexTypeTranslation(translationCA_ES);
    }

    @Override
    public void edit() {
        if (sexType.equals(baseSexType)
                && translationEN_US.equals(baseTranslationEN_US)
                && translationES_ES.equals(baseTranslationES_ES)
                && translationCA_ES.equals(baseTranslationEN_US)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.TYPE.SEX_TYPE, sexType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(sexType);
            validator.setTranslationEN_US(translationEN_US);
            validator.setTranslationES_ES(translationES_ES);
            validator.setTranslationCA_ES(translationCA_ES);

            if (validator.validate()) {
                sexType.setLastModifiedDate(new Date());
                sexType.setLastModifiedUser(user);

                Long feedback = generalController.update(sexType);
                boolean error = false;

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    error = true;
                } else {
                    translationEN_US.setReference(sexType);
                    translationES_ES.setReference(sexType);
                    translationCA_ES.setReference(sexType);
                    Long status_EN_US = translationGeneralController.update(translationEN_US);
                    Long status_ES_ES = translationGeneralController.update(translationES_ES);
                    Long status_CA_ES = translationGeneralController.update(translationCA_ES);

                    if (status_EN_US == BusinessConstant.UPDATE.FAILURE
                            || status_ES_ES == BusinessConstant.UPDATE.FAILURE
                            || status_CA_ES == BusinessConstant.UPDATE.FAILURE) {
                        generalController.update(baseSexType);

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
                        detail = messageService.getDetail(CommonConstant.TYPE.SEX_TYPE, sexType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.TYPE.SEX_TYPE);
    }

}
