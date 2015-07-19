package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PaymentTypeHelper;
import com.development.simplestockmanager.business.persistence.PaymentType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeSpecificController {

    private final PaymentTypeHelper helper;

    public PaymentTypeSpecificController() {
        this.helper = new PaymentTypeHelper();
    }
    
    public List<PaymentType> findByType(String type) {
        List<PaymentType> list = new ArrayList<>();

        try {
            Query query = helper.getFindByType(type);
            for (Object object : query.getResultList()) {
                list.add((PaymentType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<PaymentType> findAll() {
        List<PaymentType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((PaymentType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<PaymentType> findEnable() {
        List<PaymentType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((PaymentType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
