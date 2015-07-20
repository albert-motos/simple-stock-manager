package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductTypeHelper;
import com.development.simplestockmanager.business.object.nullpackage.ProductTypeNull;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeSpecificController {

    private final ProductTypeHelper helper;

    public ProductTypeSpecificController() {
        this.helper = new ProductTypeHelper();
    }

    public ProductType findByType(String type) {
        ProductType productType;

        try {
            Query query = helper.getFindByType(type);
            productType = (ProductType) query.getSingleResult();
        } catch (Exception e) {
            productType = new ProductTypeNull();
        }

        return productType;
    }
    
    public List<ProductType> findAll() {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<ProductType>  findEnable() {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<ProductType> findAllForBrowser(ProductType browser, ProductTypeTranslation translation, long status, Date createdDateFrom, Date createdDateTo,
            Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getType(), translation.getTranslation(), status, createdDateFrom, createdDateTo,
                    lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
