package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.persistence.Store;
import java.util.ArrayList;
import java.util.List;

public class StoreValidator extends BaseValidator {

    private Store store;

    public StoreValidator(long mode) {
        super(mode, null);
    }
    
    @Override
    protected void convertObject() {
        store = (Store) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (store.getName().isEmpty()) {
            fieldsEmptyList.add("Name");
        }

        if (store.getStreet().isEmpty()) {
            fieldsEmptyList.add("Street");
        }

        if (store.getCity().isEmpty()) {
            fieldsEmptyList.add("City");
        }

        if (store.getState().isEmpty()) {
            fieldsEmptyList.add("State");
        }

        if (store.getCountry().isEmpty()) {
            fieldsEmptyList.add("Country");
        }
        
        if (store.getPhone().isEmpty()) {
            fieldsEmptyList.add("Phone number");
        }

//        if (store.getManagerID() == 0) {
//            fieldsEmptyList.add("Manager selector: this selector is not indicated");
//        }
        
        return fieldsEmptyList;
    }
    
}
