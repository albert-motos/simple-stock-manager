/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.nullpackage.ClientNull;
import com.simplestockmanager.helper.ClientHelper;
import com.simplestockmanager.persistence.Client;
import com.simplestockmanager.persistence.controller.ClientJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ClientGeneralController {

    public static long create(String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, boolean isEnable, Date createdDate,
            Date lastModifiedDate) {

        Client client = new Client(firstName, lastName, bornDate, sexTypeID, phone, email, isEnable, createdDate, lastModifiedDate);

        try {
            ClientJpaController clientJpaController = ClientHelper.getJpaController();
            clientJpaController.create(client);
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client.getId();
    }

    public static Client read(long id) {

        Client client;

        try {
            Query query = ClientHelper.getFindByIdQuery(id);
            client = (Client) query.getSingleResult();
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client;
    }

    public static long update(long id, String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, boolean isEnable,
            Date createdDate, Date lastModifiedDate) {

        long status = Constant.UPDATE.FAILURE;

        if (read(id).getId() != Constant.IDENTIFIER.INVALID) {
            Client client = new Client(id, firstName, lastName, bornDate, sexTypeID, phone, email, isEnable, createdDate, lastModifiedDate);

            try {
                ClientJpaController clientJpaController = ClientHelper.getJpaController();
                clientJpaController.edit(client);
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
                ClientJpaController clientJpaController = ClientHelper.getJpaController();
                clientJpaController.destroy(id);
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
