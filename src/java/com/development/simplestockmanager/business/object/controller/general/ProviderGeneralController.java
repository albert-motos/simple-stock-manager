package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.ProviderNull;
import com.development.simplestockmanager.business.object.helper.ProviderHelper;
import com.development.simplestockmanager.business.persistence.old.Provider;
import com.development.simplestockmanager.business.persistence.controller.ProviderJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ProviderGeneralController {

    public long create(Provider provider) {
        try {
            ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
            providerJpaController.create(provider);
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider.getId();
    }

    public Provider read(Provider provider) {
        try {
            Query query = ProviderHelper.getFindByIdQuery(provider.getId());
            provider = (Provider) query.getSingleResult();
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider;
    }

    public long update(Provider provider) {
        long status = Constant.UPDATE.FAILURE;

        if (read(provider).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
                providerJpaController.edit(provider);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public long delete(Provider provider) {
        long status = Constant.DELETE.FAILURE;

        if (read(provider).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
                providerJpaController.destroy(provider.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
