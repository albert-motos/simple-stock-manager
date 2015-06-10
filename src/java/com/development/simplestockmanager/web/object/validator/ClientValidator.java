package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.persistence.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientValidator extends BaseValidator {

    private Client client;

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

        if (client.getFirstName().isEmpty()) {
            fieldsEmptyList.add("First name");
        }

        if (client.getLastName().isEmpty()) {
            fieldsEmptyList.add("Last name");
        }

        if (client.getPhone().isEmpty()) {
            fieldsEmptyList.add("Phone number");
        }

        if (client.getEmail().isEmpty()) {
            fieldsEmptyList.add("Email");
        }

        if (client.getBornDate() == null) {
            fieldsEmptyList.add("Born date");
        }

        if (client.getSexTypeID() == 0) {
            fieldsEmptyList.add("Sex type selector: this selector is not indicated");
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (client.getBornDate() != null) {
            if (client.getBornDate().after(new Date())) {
                causeList.add("Born date: The date may not be later than today");
            }
        }

        return causeList;
    }

}
