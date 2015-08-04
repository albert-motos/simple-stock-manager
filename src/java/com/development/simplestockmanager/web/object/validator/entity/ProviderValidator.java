package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.constant.CommonConstant;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Provider object
 *
 * @author foxtrot
 */
public class ProviderValidator extends CommonValidator implements BaseValidator {

    private Provider provider;

    public ProviderValidator(long mode) {
        super(mode);
    }

    @Override
    public void setObject(Object object) {
        provider = (Provider) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (provider.getName().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.NAME, null));
        }

        if (provider.getIdentifier().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.IDENTIFIER, null));
        }

        if (provider.getPhone().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PHONE_NUMBER, null));
        }

        if (provider.getEmail().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.EMAIL, null));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
