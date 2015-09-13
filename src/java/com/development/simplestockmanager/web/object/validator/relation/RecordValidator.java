package com.development.simplestockmanager.web.object.validator.relation;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.RecordSpecificController;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for Record object
 *
 * @author foxtrot
 */
public class RecordValidator extends CommonValidator implements BaseValidator {

    private final RecordSpecificController specificController;
    private Record record;

    public RecordValidator(long mode, RecordSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
    }

    @Override
    public void setObject(Object object) {
        record = (Record) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (!record.getAmount().equals(BigDecimal.ZERO) && record.getNote().isEmpty()) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.NOTE, null));
        }

        return fieldsEmptyList;
    }
    
    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
