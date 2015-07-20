package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.ProductSpecificController;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Product object
 *
 * @author foxtrot
 */
public class ProductValidator extends CommonValidator implements BaseValidator {

    private final ProductSpecificController specificController;
    private Product product;

    public ProductValidator(long mode, LanguageController controller, ProductSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
    }

    @Override
    public void setObject(Object object) {
        product = (Product) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
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
    public List<String> inconsistenceFields() {
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
