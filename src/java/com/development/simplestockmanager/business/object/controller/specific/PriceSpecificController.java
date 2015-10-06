package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductHelper;
import com.development.simplestockmanager.business.object.nullpackage.ProductNull;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class PriceSpecificController {
    
    private final ProductHelper helper;

    public PriceSpecificController() {
        helper = new ProductHelper();
    }

    public Product findByRelation(ProductType productType, Brand brand, Provider provider) {
        Product product;
        
        try {
            Query query = helper.getFindByRelationQuery(productType, brand, provider);
            product = (Product) query.getSingleResult();
        } catch (Exception e) {
            product = new ProductNull();
        }
        
        return product;
    }
    
    public List<Product> findAllForBrowser(Product browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Product> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getBrand().getId(), browser.getDescription(), browser.getName(), browser.getProductType().getId(),
                    browser.getProvider().getId(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Product) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<Product> findAllByBrowser(String browser) {
        List<Product> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Product) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Product> findEnableByBrowser(String browser) {
        List<Product> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Product) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
    public List<Product> findEnableByStore(String browser, String store) {
        List<Product> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByStore(browser, store);
            for (Object object : query.getResultList()) {
                list.add((Product) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
