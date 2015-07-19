package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Store object
 *
 * @author foxtrot
 */
public class StoreValidator extends CommonValidator implements BaseValidator {

    private Store store;

    public StoreValidator(long mode, LanguageController controller) {
        super(mode, controller);
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
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }

        if (store.getPhone().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }
        
        if (store.getStreet().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.STREET));
        }

        if (store.getCity().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.CITY));
        }

        if (store.getState().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.STATE));
        }

        if (store.getCountry().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.COUNTRY));
        }

        if (store.getEmployee().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.EMPLOYEE));
        }
        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
