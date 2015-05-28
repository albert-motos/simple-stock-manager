/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static long create(String name, String identifier, String phone, String email, boolean isEnable, Date createdDate, Date lastModifiedDate) {

        Provider provider = new Provider(name, identifier, phone, email, isEnable, createdDate, lastModifiedDate);

        try {
            ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
            providerJpaController.create(provider);
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider.getId();
    }

    public static Provider read(long id) {

        Provider provider;

        try {
            Query query = ProviderHelper.getFindByIdQuery(id);
            provider = (Provider) query.getSingleResult();
        } catch (Exception e) {
            provider = new ProviderNull();
        }

        return provider;
    }

    public static long update(long id, String name, String identifier, String phone, String email, boolean isEnable, Date createdDate, Date lastModifiedDate) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Provider provider = new Provider(id, name, identifier, phone, email, isEnable, createdDate, lastModifiedDate);

            try {
                ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
                providerJpaController.edit(provider);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = Constant.DELETE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ProviderJpaController providerJpaController = ProviderHelper.getJpaController();
                providerJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}
