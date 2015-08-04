package com.development.simplestockmanager.web.object.validator.type;

import com.development.simplestockmanager.common.web.object.validator.common.CommonValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseTypeValidator;
import com.development.simplestockmanager.common.web.object.validator.base.BaseValidator;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeSpecificController;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeTranslationSpecificController;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validator class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeValidator extends CommonValidator implements BaseValidator, BaseTypeValidator {

    private final PaymentTypeSpecificController specificController;
    private final PaymentTypeTranslationSpecificController translationController;

    private PaymentType paymentType;
    private PaymentTypeTranslation translationES_ES;
    private PaymentTypeTranslation translationCA_ES;
    private PaymentTypeTranslation translationEN_US;

    public PaymentTypeValidator(long mode, PaymentTypeSpecificController specificController) {
        super(mode);
        this.specificController = specificController;
        this.translationController = new PaymentTypeTranslationSpecificController();
    }

    @Override
    public void setObject(Object object) {
        paymentType = (PaymentType) object;
    }

    @Override
    public void setTranslationES_ES(Object object) {
        translationES_ES = (PaymentTypeTranslation) object;
    }

    @Override
    public void setTranslationCA_ES(Object object) {
        translationCA_ES = (PaymentTypeTranslation) object;
    }

    @Override
    public void setTranslationEN_US(Object object) {
        translationEN_US = (PaymentTypeTranslation) object;
    }

    @Override
    public boolean validate() {
        return validate(checkFields(), inconsistenceFields());
    }

    @Override
    public List<String> checkFields() {
        List<String> fieldsEmptyList = new ArrayList<>();

        if (paymentType.getType().isEmpty()) {
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

        if (!paymentType.getType().isEmpty()) {
            PaymentType paymentTypeOfType = specificController.findByType(paymentType.getType());

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && paymentTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && paymentTypeOfType.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(paymentTypeOfType.getId(), paymentType.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TYPE, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        if (!translationEN_US.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationEN_US.getTranslation(), CommonConstant.LANGUAGE.EN_US);

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.EN_US, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        if (!translationES_ES.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationES_ES.getTranslation(), CommonConstant.LANGUAGE.ES_ES);

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.ES_ES, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        if (!translationCA_ES.getTranslation().isEmpty()) {
            PaymentTypeTranslation translation = translationController.findByTranslationAndLanguage(translationCA_ES.getTranslation(), CommonConstant.LANGUAGE.CA_ES);

            if ((mode == WebConstant.VALIDATOR.MODE.CREATE && translation.getId() != BusinessConstant.IDENTIFIER.INVALID)
                    || (mode == WebConstant.VALIDATOR.MODE.EDIT && translation.getId() != BusinessConstant.IDENTIFIER.INVALID
                    && !Objects.equals(translation.getId(), paymentType.getId()))) {
                causeList.add(messageService.getDetail(CommonConstant.LABEL.TRANSLATION.CA_ES, CommonConstant.MESSAGE.DETAIL.ERROR.UNIQUE));
            }
        }

        return causeList;
    }

}
