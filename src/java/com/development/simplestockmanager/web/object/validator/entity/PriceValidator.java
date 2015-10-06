package com.development.simplestockmanager.web.object.validator.entity;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.PriceSpecificController;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Price object
 *
 * @author foxtrot
 */
public class PriceValidator extends CommonValidator implements BaseValidator {

    private final PriceSpecificController specificController;
    private Price price;

    public PriceValidator(long mode, PriceSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
    }

    @Override
    public void setObject(Object object) {
        price = (Price) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (price.getTitle().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.NAME, null));
        }
        
        if (price.getInitialDate() == null) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.BEGIN_DATE, CommonConstant.MESSAGE.DETAIL.WARNING.DATE));
        }
        
        if (price.getEndDate()== null) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.END_DATE, CommonConstant.MESSAGE.DETAIL.WARNING.DATE));
        }
        
        if (price.getPriceType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PRICE_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }
        
        if (price.getStock().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.STOCK, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();
        
        if (price.getInitialDate() != null && price.getEndDate() != null) {
            if (price.getInitialDate().after(price.getEndDate())) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.BEGIN_DATE, CommonConstant.MESSAGE.DETAIL.ERROR.BEGIN_DATE));
            }
        }
        
        return causeList;
    }

}
