package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Brand object
 *
 * @author foxtrot
 */
public class BrandValidator extends BaseValidator {

    private final BrandSpecificController specificController;
    private Brand brand;

    public BrandValidator(long mode, InternationalizationController controller) {
        super(mode, controller);
        specificController = new BrandSpecificController();
    }
    
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
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }
        
        if (brand.getDescription().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.DESCRIPTION));
        }

        return fieldsEmptyList;
    }

    @Override
    protected List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (!brand.getName().isEmpty()) {
            if (!specificController.nameIsAvailable(brand.getName())) {
                causeList.add(controller.getWord(CommonConstant.MESSAGE.ERROR.NAME));
            }
        }

        return causeList;
    }

}
