package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.persistence.Brand;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Brand object
 *
 * @author foxtrot
 */
public class BrandSpecificController {

    private final BrandHelper helper;

    public BrandSpecificController() {
        helper = new BrandHelper();
    }

    public List<Brand> findAllForBrowser(Brand browser, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Brand> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getName(), browser.getDescription(), createdDateFrom, createdDateTo, lastModifiedDateFrom,
                    lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Brand) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public Brand findByName(String name) {
        Brand brand;

        try {
            Query query = helper.getFindByNameQuery(name);
            brand = (Brand) query.getSingleResult();
        } catch (Exception e) {
            brand = new BrandNull();
        }

        return brand;
    }

    public List<Brand> fillSelectorByName(String name) {
        List<Brand> list = new ArrayList<>();

        try {
            Query query = helper.getFindByNameForSelectorQuery(name);
            for (Object object : query.getResultList()) {
                list.add((Brand) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
