package com.development.simplestockmanager.web.object.validator.relation;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.common.constant.CommonConstant;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Invoice object
 *
 * @author foxtrot
 */
public class InvoiceValidator extends CommonValidator implements BaseValidator {

    private Invoice invoice;

    public InvoiceValidator(long mode) {
        super(mode);
    }

    @Override
    public void setObject(Object object) {
        invoice = (Invoice) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (invoice.getClient().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.CLIENT, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }
        
        if (invoice.getPaymentType().getId() == BusinessConstant.IDENTIFIER.INVALID) {
            fieldsEmptyList.add(messageService.getDetail(CommonConstant.LABEL.PAYMENT_TYPE, CommonConstant.MESSAGE.DETAIL.WARNING.SELECTOR));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
