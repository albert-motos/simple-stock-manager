package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.ProductSpecificController;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductValidator extends BaseValidator {

    private final ProductSpecificController specificController;
    private Product product;

    public ProductValidator(long mode, LanguageController controller) {
        super(mode, controller);
        specificController = new ProductSpecificController();
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
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }

        if (product.getProductType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PRODUCT_TYPE));
        }

        if (product.getDescription().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.DESCRIPTION));
        }

        if (product.getBrand().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.BRAND));
        }

        if (product.getProvider().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PROVIDER));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (product.getProvider().getId() != BusinessConstant.IDENTIFIER.INVALID
                && product.getBrand().getId() != BusinessConstant.IDENTIFIER.INVALID
                && product.getProductType().getId() != BusinessConstant.IDENTIFIER.INVALID) {
            
            Product productOfRelation = specificController.findByRelation(product.getProductType(), product.getBrand(), product.getProvider());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && productOfRelation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && productOfRelation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(productOfRelation.getId(), product.getId()))) {
               causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.PRODUCT));
            }
        }

        return causeList;
    }

}
