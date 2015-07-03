package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.persistence.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator extends BaseValidator {

    private Product product;

    public ProductValidator(long mode) {
        super(mode, null);
    }
    
    @Override
    protected void convertObject() {
        product = (Product) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (product.getName().isEmpty()) {
            fieldsEmptyList.add("Name");
        }
        
        if (product.getDescription().isEmpty()) {
            fieldsEmptyList.add("Description");
        }

        return fieldsEmptyList;
    }
    
}
