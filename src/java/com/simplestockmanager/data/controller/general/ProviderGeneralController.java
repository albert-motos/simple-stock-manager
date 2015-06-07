package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.ProviderNull;
import com.simplestockmanager.helper.ProviderHelper;
import com.simplestockmanager.persistence.Provider;
import com.simplestockmanager.persistence.controller.ProviderJpaController;
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
