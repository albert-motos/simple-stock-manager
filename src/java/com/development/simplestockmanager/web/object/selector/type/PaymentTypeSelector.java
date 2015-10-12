package com.development.simplestockmanager.web.object.selector.type;

import com.development.simplestockmanager.common.web.object.selector.common.CommonTypeSelector;
import com.development.simplestockmanager.business.object.controller.specific.PaymentTypeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeNull;
import com.development.simplestockmanager.business.object.nullpackage.PaymentTypeTranslationNull;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeSelector extends CommonTypeSelector implements BaseSelector {

    private final PaymentTypeSpecificController specificController;
    private HashMap<String, PaymentType> hashMap;

    public PaymentTypeSelector(long mode) {
        super(mode);
        this.specificController = new PaymentTypeSpecificController();
        clear();
    }
    
    public PaymentTypeSelector(long mode, PaymentType paymentType) {
        this(mode);
        this.selection = getDisplayName(getTranslation(paymentType));
    }    
    
    @Override
    public void search() {
        List<PaymentType> paymentTypeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            paymentTypeList = specificController.findAll();
        } else {
            paymentTypeList = specificController.findEnable();
        }

        for (PaymentType paymentType : paymentTypeList) {
            String key = getDisplayName(getTranslation(paymentType));
            hashMap.put(key, paymentType);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
        selection = "";
        
        search();
    }
    
    public PaymentType getSelectedValue() {
        PaymentType paymentType = new PaymentTypeNull();

        if (!selection.isEmpty()) {
            paymentType = hashMap.get(selection);
        }

        return paymentType;
    }
    
    public String getDisplayName(PaymentType paymentType) {
        return (paymentType != null ? getDisplayName(getTranslation(paymentType)) : "");
    }
    
    private String getDisplayName(PaymentTypeTranslation paymentTypeTranslation) {
        return paymentTypeTranslation.getTranslation();
    }
    
    private PaymentTypeTranslation getTranslation(PaymentType paymentType) {
        PaymentTypeTranslation translation = new PaymentTypeTranslationNull();
        
        for (PaymentTypeTranslation paymentTypeTranslation : paymentType.getPaymentTypeTranslationList()) {
            if (paymentTypeTranslation.getLanguage().getCode().equals(language)) {
                translation = paymentTypeTranslation;
            }
        }
        
        return translation;
    }
    
}
