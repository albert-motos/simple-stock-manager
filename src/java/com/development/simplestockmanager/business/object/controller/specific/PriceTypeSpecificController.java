package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.PriceTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.PriceTypeNull;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<PriceType> findAllForBrowser(PriceType browser, PriceTypeTranslation translation, long status, Date createdDateFrom, Date createdDateTo,
            Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        List<PriceType> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getType(), translation.getTranslation(), status, createdDateFrom, createdDateTo,
                    lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((PriceType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
