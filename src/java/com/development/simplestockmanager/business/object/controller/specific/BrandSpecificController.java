package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
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
        Query query = helper.getFindByNameQuery(name);

        return query.getResultList().isEmpty();
    }
}
