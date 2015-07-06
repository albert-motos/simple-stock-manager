package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.object.controller.specific.ProductSpecificController;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.Constant;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator extends BaseValidator {

    private final ProductSpecificController specificController;
    private Product product;

    public ProductValidator(long mode, InternationalizationController controller) {
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
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.NAME));
        }

        if (product.getProductType().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.PRODUCT_TYPE));
        }

        if (product.getDescription().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.DESCRIPTION));
        }

        if (product.getBrand().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.BRAND));
        }

        if (product.getProvider().getId() == Constant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(controller.getWord(InternationalizationConstant.MESSAGE.WARNING.PROVIDER));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (product.getProvider().getId() != Constant.IDENTIFIER.INVALID
                && product.getBrand().getId() != Constant.IDENTIFIER.INVALID
                && product.getProductType().getId() != Constant.IDENTIFIER.INVALID) {
            if (!specificController.relationIsAvailable(product.getProductType(), product.getBrand(), product.getProvider())) {
                causeList.add(controller.getWord(InternationalizationConstant.MESSAGE.ERROR.PRODUCT));
            }
        }

        return causeList;
    }

}
