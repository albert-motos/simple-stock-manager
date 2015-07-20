package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Brand object
 *
 * @author foxtrot
 */
public class BrandValidator extends CommonValidator implements BaseValidator {

    private final BrandSpecificController specificController;
    private Brand brand;

    public BrandValidator(long mode, LanguageController controller, BrandSpecificController specificController) {
        super(mode, controller);
        this.specificController = specificController;
    }
    
    @Override
    public void setObject(Object object) {
        brand = (Brand) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (brand.getName().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }
        
        if (brand.getDescription().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.DESCRIPTION));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (!brand.getName().isEmpty()) {
            Brand brandOfName = specificController.findByName(brand.getName());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && brandOfName.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && brandOfName.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(brandOfName.getId(), brand.getId()))) {
                causeList.add(languageController.getWord(CommonConstant.MESSAGE.ERROR.NAME));
            }
        }

        return causeList;
    }

}
