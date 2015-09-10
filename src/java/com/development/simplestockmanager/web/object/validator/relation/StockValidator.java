package com.development.simplestockmanager.web.object.validator.relation;

import com.development.simplestockmanager.web.object.validator.entity.*;
import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.StockSpecificController;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Stock object
 *
 * @author foxtrot
 */
public class StockValidator extends CommonValidator implements BaseValidator {

    private final StockSpecificController specificController;
    private Stock stock;

    public StockValidator(long mode, StockSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
    }

    @Override
    public void setObject(Object object) {
        stock = (Stock) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (stock.getStore().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.STORE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }
        
        if (stock.getProduct().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PRODUCT, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        List<String> causeList = new ArrayList<>();

        if (stock.getStore().getId() != BusinessConstant.IDENTIFIER.INVALID
                && stock.getProduct().getId() != BusinessConstant.IDENTIFIER.INVALID) {

            Stock stockOfRelation = specificController.findByRelation(stock.getProduct(), stock.getStore());

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && stockOfRelation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && stockOfRelation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(stockOfRelation.getId(), stock.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.STOCK, CommonConstant.MESSAGE.DETAIL.ERROR.STOCK));
            }
        }

        return causeList;
    }

}
