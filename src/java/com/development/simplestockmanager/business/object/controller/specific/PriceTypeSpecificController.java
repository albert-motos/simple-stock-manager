package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PriceTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.persistence.PriceType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeSpecificController {

    private final PriceTypeHelper helper;

    public PriceTypeSpecificController() {
        this.helper = new PriceTypeHelper();
    }
    
    public PriceType findByType(String type) {
        PriceType priceType;

        try {
            Query query = helper.getFindByType(type);
            priceType = (PriceType) query.getSingleResult();
        } catch (Exception e) {
            priceType = new PriceTypeNull();
        }

        return priceType;
    }

    public List<PriceType> findAll() {
        List<PriceType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((PriceType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<PriceType> findEnable() {
        List<PriceType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((PriceType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
