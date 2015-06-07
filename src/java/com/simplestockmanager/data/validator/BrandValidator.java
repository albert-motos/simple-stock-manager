package com.simplestockmanager.data.validator;

import com.simplestockmanager.data.controller.specific.BrandSpecificController;
import com.simplestockmanager.persistence.Brand;
import java.util.ArrayList;
import java.util.List;

public class BrandValidator extends BaseValidator {

    private Brand brand;

    @Override
    protected void convertObject() {
        brand = (Brand) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (brand.getName().isEmpty()) {
            fieldsEmptyList.add("Name");
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (!brand.getName().isEmpty()) {
            if (!BrandSpecificController.nameIsAvailable(brand.getName())) {
                causeList.add("Name: This name is already in use, change it");
            }
        }

        return causeList;
    }

}
