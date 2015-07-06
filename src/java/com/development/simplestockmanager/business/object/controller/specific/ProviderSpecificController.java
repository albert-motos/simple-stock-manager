package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
import com.development.simplestockmanager.business.object.helper.ProviderHelper;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ProviderSpecificController {
    
    private final ProviderHelper helper;

    public ProviderSpecificController() {
        helper = new ProviderHelper();
    }

    public boolean nameIsAvailable(String name) {
        Query query = helper.getFindByNameForSelectorQuery(name);

        return query.getResultList().isEmpty();
    }
    
    public List<Provider> fillSelectorByName(String name) {
        List<Provider> list = new ArrayList<>();

        try {
            Query query = helper.getFindByNameForSelectorQuery(name);
            for (Object object : query.getResultList()) {
                list.add((Provider) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        
        System.out.println("# " + list);

        return list;
    }
}
