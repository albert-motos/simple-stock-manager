package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.persistence.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class BrandSpecificController {
    
    private final BrandHelper helper;

    public BrandSpecificController() {
        helper = new BrandHelper();
    }

    public List<Brand> findAll() {
        List<Brand> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllQuery();
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
