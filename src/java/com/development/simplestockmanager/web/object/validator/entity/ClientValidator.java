package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.common.constant.CommonConstant;
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

    public ClientValidator(long mode) {
        super(mode);
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
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.FIRSTNAME, null));
        }

        if (client.getLastname().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.LASTNAME, null));
        }

        if (client.getPhone().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PHONE_NUMBER, null));
        }

        if (client.getEmail().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.EMAIL, null));
        }

        if (client.getBornDate() == null) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.BORN_DATE, null));
        }

        if (client.getSexType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.SEX_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (client.getBornDate() != null) {
            if (client.getBornDate().after(new Date())) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.BORN_DATE, CommonConstant.MESSAGE.DETAIL.ERROR.DATE));
            }
        }

        return causeList;
    }

}
