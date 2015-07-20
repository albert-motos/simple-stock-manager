package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.business.persistence.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validator class for Client object
 *
 * @author foxtrot
 */
public class ClientValidator extends CommonValidator implements BaseValidator {

    private Client client;

    public ClientValidator(long mode, LanguageController controller) {
        super(mode, controller);
    }

    @Override
    public void setObject(Object object) {
        client = (Client) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (client.getFirstname().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.FIRSTNAME));
        }

        if (client.getLastname().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.LASTNAME));
        }

        if (client.getPhone().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (client.getEmail().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.EMAIL));
        }

        if (client.getBornDate() == null) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.BORN_DATE));
        }

        if (client.getSexType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.SEX_TYPE));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (client.getBornDate() != null) {
            if (client.getBornDate().after(new Date())) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.BORN_DATE));
            }
        }

        return causeList;
    }

}
