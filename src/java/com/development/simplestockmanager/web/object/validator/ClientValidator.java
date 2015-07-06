package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.business.persistence.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validator class for Client object
 *
 * @author foxtrot
 */
public class ClientValidator extends BaseValidator {

    private Client client;

    public ClientValidator(long mode, InternationalizationController controller) {
        super(mode, controller);
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
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.FIRSTNAME));
        }

        if (client.getLastname().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.LASTNAME));
        }

        if (client.getPhone().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (client.getEmail().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.EMAIL));
        }

        if (client.getBornDate() == null) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.BORN_DATE));
        }

        if (client.getSexType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.SEX_TYPE));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (client.getBornDate() != null) {
            if (client.getBornDate().after(new Date())) {
                causeList.add(controller.getWord(CommonConstant.MESSAGE.ERROR.BORN_DATE));
            }
        }

        return causeList;
    }

}
