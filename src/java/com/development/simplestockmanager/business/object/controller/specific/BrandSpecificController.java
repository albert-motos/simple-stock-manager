package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
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

    public boolean nameIsAvailable(String name) {
        Query query = helper.getFindByNameForSelectorQuery(name);

        return query.getResultList().isEmpty();
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
