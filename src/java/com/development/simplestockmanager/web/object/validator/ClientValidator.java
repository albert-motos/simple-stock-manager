package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientValidator extends BaseValidator {

    private Client client;

    public ClientValidator(long mode, String language) {
        super(mode, language);
    }

    @Override
    protected void convertObject() {
        client = (Client) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (client.getFirstname().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.FIRSTNAME));
        }

        if (client.getLastname().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.LASTNAME));
        }

        if (client.getPhone().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (client.getEmail().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.EMAIL));
        }

        if (client.getBornDate() == null) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.BORN_DATE));
        }

        if (client.getSexType() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.SEX_TYPE));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (client.getBornDate() != null) {
            if (client.getBornDate().after(new Date())) {
                causeList.add(controller.getWord(InternationalizationConstant.MESSAGE.ERROR.BORN_DATE));
            }
        }

        return causeList;
    }

}
