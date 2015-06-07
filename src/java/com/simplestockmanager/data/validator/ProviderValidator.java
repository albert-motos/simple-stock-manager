package com.simplestockmanager.data.validator;

import com.simplestockmanager.persistence.Provider;
import java.util.ArrayList;
import java.util.List;

public class ProviderValidator extends BaseValidator {

    private Provider provider;

    @Override
    protected void convertObject() {
        provider = (Provider) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (provider.getName().isEmpty()) {
            fieldsEmptyList.add("Name");
        }

        if (provider.getIdentifier().isEmpty()) {
            fieldsEmptyList.add("Identifier");
        }

        if (provider.getPhone().isEmpty()) {
            fieldsEmptyList.add("Phone number");
        }

        if (provider.getEmail().isEmpty()) {
            fieldsEmptyList.add("Email");
        }

        return fieldsEmptyList;
    }

}
