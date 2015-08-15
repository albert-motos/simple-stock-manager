package com.development.simplestockmanager.web.controller.edit.type;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.type.EmployeeTypeCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for EmployeeType object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeTypeEdit")
@ViewScoped
public class EmployeeTypeEditController extends EmployeeTypeCommonController implements EditController {

    private EmployeeType baseEmployeeType;
    private final EmployeeTypeTranslation baseTranslationEN_US;
    private final EmployeeTypeTranslation baseTranslationES_ES;
    private final EmployeeTypeTranslation baseTranslationCA_ES;

    public EmployeeTypeEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);

        try {
            employeeType = (EmployeeType) receiveObjectFromSession(WebConstant.SESSION.EMPLOYEE_TYPE);
            baseEmployeeType = new EmployeeType(employeeType);
        } catch (Exception e) {
            back();
        }

        for (EmployeeTypeTranslation employeeTypeTranslation : employeeType.getEmployeeTypeTranslationList()) {
            if (employeeTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.EN_US)) {
                translationEN_US = employeeTypeTranslation;
            }

            if (employeeTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.ES_ES)) {
                translationES_ES = employeeTypeTranslation;
            }

            if (employeeTypeTranslation.getLanguage().getCode().equals(CommonConstant.LANGUAGE.CA_ES)) {
                translationCA_ES = employeeTypeTranslation;
            }
        }

        baseTranslationEN_US = new EmployeeTypeTranslation(translationEN_US);
        baseTranslationES_ES = new EmployeeTypeTranslation(translationES_ES);
        baseTranslationCA_ES = new EmployeeTypeTranslation(translationCA_ES);
    }

    @Override
    public void edit() {
        if (employeeType.equals(baseEmployeeType)
                && translationEN_US.equals(baseTranslationEN_US)
                && translationES_ES.equals(baseTranslationES_ES)
                && translationCA_ES.equals(baseTranslationCA_ES)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.TYPE.EMPLOYEE_TYPE, employeeType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(employeeType);
            validator.setTranslationEN_US(translationEN_US);
            validator.setTranslationES_ES(translationES_ES);
            validator.setTranslationCA_ES(translationCA_ES);

            if (validator.validate()) {
                employeeType.setLastModifiedDate(new Date());
                employeeType.setLastModifiedUser(user);
//                Employee employee = new Employee(user);
//                employee.setEmployeeType(new EmployeeTypeNull());
//                employeeType.setLastModifiedUser(employee);
//                employee = new Employee(employeeType.getCreatedUser());
//                employee.setEmployeeType(new EmployeeTypeNull());
//                employeeType.setCreatedUser(employee);

                Long feedback = generalController.update(employeeType);
                boolean error = false;

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    error = true;
                } else {
                    translationEN_US.setReference(employeeType);
                    translationES_ES.setReference(employeeType);
                    translationCA_ES.setReference(employeeType);
                    Long status_EN_US = translationGeneralController.update(translationEN_US);
                    Long status_ES_ES = translationGeneralController.update(translationES_ES);
                    Long status_CA_ES = translationGeneralController.update(translationCA_ES);

                    if (status_EN_US == BusinessConstant.UPDATE.FAILURE
                            || status_ES_ES == BusinessConstant.UPDATE.FAILURE
                            || status_CA_ES == BusinessConstant.UPDATE.FAILURE) {
                        generalController.update(baseEmployeeType);

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
                        detail = messageService.getDetail(CommonConstant.TYPE.EMPLOYEE_TYPE, employeeType.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.TYPE.EMPLOYEE_TYPE);
    }

}
