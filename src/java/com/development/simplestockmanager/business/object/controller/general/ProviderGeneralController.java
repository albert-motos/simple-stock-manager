package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.ProviderNull;
import com.development.simplestockmanager.business.object.helper.ProviderHelper;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.Date;
import javax.persistence.Query;

/**
 * General controller class for Provider object
 *
 * @author foxtrot
 */
public class ProviderGeneralController {
    
    private final ProviderJpaController controller;

    public ProviderGeneralController() {
        ProviderHelper helper = new ProviderHelper();
        controller = helper.getJpaController();
    }

    public long create(Provider provider) {
        try {
            controller.create(provider);
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider.getId();
    }

    public Provider read(Provider provider) {
        try {
            provider = controller.findProvider(provider.getId());

            if (provider == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider;
    }

//    
    public long update(Provider provider) {
        long status;

        try {
            controller.edit(provider);
            status = Constant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = Constant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Provider provider) {
        long status;

        try {
            controller.destroy(provider.getId());
            status = Constant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = Constant.DELETE.FAILURE;
        }

        return status;
    }
    
}
