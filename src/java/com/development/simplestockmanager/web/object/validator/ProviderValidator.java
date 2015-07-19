package com.development.simplestockmanager.web.object.validator;

import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.common.language.LanguageController;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator class for Provider object
 *
 * @author foxtrot
 */
public class ProviderValidator extends CommonValidator implements BaseValidator {

    private Provider provider;

    public ProviderValidator(long mode, LanguageController controller) {
        super(mode, controller);
    }

    @Override
    public void setObject(Object object) {
        provider = (Provider) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (provider.getName().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.NAME));
        }

        if (provider.getIdentifier().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.IDENTIFIER));
        }

        if (provider.getPhone().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.PHONE_NUMBER));
        }

        if (provider.getEmail().isEmpty()) {
            fieldsEmptyList.add(languageController.getWord(CommonConstant.MESSAGE.WARNING.EMAIL));
        }

        return fieldsEmptyList;
    }

    @Override
    public List<String> inconsistenceFields() {
        return new ArrayList<>();
    }

}
