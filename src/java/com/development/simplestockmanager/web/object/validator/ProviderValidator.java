package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Provider object
 *
 * @author foxtrot
 */
public class ProviderValidator extends BaseValidator {

    private Provider provider;

    public ProviderValidator(long mode, InternationalizationController controller) {
        super(mode, controller);
    }

    @Override
    protected void convertObject() {
        provider = (Provider) object;
    }

    @Override
    public boolean validate() {
        convertObject();
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    protected List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (provider.getName().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }

        if (provider.getIdentifier().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.IDENTIFIER));
        }

        if (provider.getPhone().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (provider.getEmail().isEmpty()) {
            fieldsEmptyList.add(controller.getWord(CommonConstant.MESSAGE.WARNING.EMAIL));
        }

        return fieldsEmptyList;
    }

}
