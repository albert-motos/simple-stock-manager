package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.constant.CommonConstant;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Store object
 *
 * @author foxtrot
 */
public class StoreValidator extends CommonValidator implements BaseValidator {

    private Store store;

    public StoreValidator(long mode) {
        super(mode);
    }

    @Override
    public void setObject(Object object) {
        store = (Store) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (store.getName().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.NAME, null));
        }

        if (store.getPhone().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PHONE_NUMBER, null));
        }
        
        if (store.getStreet().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.STREET, null));
        }

        if (store.getCity().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.CITY, null));
        }

        if (store.getState().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.STATE, null));
        }

        if (store.getCountry().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.COUNTRY, null));
        }

        if (store.getEmployee().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.EMPLOYEE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }
        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
