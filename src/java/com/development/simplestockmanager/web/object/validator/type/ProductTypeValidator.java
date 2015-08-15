package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseTypeValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.ProductTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.ProductTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final ProductTypeSpecificController specificController;
    private final ProductTypeTranslationSpecificController translationController;
   
    private ProductType productType;
    private ProductTypeTranslation translationES_ES;
    private ProductTypeTranslation translationCA_ES;
    private ProductTypeTranslation translationEN_US;
    

    public ProductTypeValidator(long mode, ProductTypeSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
        this.translationController = new ProductTypeTranslationSpecificController();
    }
    
    @Override
    public void setObject(Object object) {
        productType = (ProductType) object;
    }
    
    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (ProductTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (ProductTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (ProductTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (productType.getType().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.TYPE, null));
        }
        
        if (translationEN_US.getTranslation().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.EN_US, null));
        }
        
        if (translationES_ES.getTranslation().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.ES_ES, null));
        }
        
        if (translationCA_ES.getTranslation().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.CA_ES, null));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (!productType.getType().isEmpty()) {
            ProductType productTypeOfType = specificController.findByType(productType.getType());
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && productTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && productTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(productTypeOfType.getId(), productType.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TYPE, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }
        
        if (!translationEN_US.getTranslation().isEmpty()) {
            ProductTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), translationEN_US.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.EN_US, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }
        
        if (!translationES_ES.getTranslation().isEmpty()) {
            ProductTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), translationES_ES.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.ES_ES, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }
        
        if (!translationCA_ES.getTranslation().isEmpty()) {
            ProductTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);
            
            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), translationCA_ES.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.CA_ES, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        return causeList;
    }

}
