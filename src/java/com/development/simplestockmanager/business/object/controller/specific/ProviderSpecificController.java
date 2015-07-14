package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProviderHelper;
import com.development.simplestockmanager.business.persistence.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Provider object
 * 
 * @author foxtrot
 */
public class ProviderSpecificController {
    
    private final ProviderHelper helper;

    public ProviderSpecificController() {
        helper = new ProviderHelper();
    }

    public List<Provider> getFindAllByBrowser(String browser) {
        List<Provider> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Provider) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Provider> getFindEnableByBrowser(String browser) {
        List<Provider> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnableByBrowser(browser);
            for (Object object : query.getResultList()) {
                list.add((Provider) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
    
}
