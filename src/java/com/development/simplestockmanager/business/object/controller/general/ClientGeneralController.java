package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.ClientNull;
import com.development.simplestockmanager.business.object.helper.ClientHelper;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.business.persistence.controller.ClientJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Client object
 *
 * @author foxtrot
 */
public class ClientGeneralController {

    private final ClientJpaController controller;

    public ClientGeneralController() {
        ClientHelper helper = new ClientHelper();
        controller = helper.getJpaController();
    }

    public long create(Client client) {
        try {
            controller.create(client);
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client.getId();
    }

    public Client read(Client client) {
        try {
            client = controller.findClient(client.getId());

            if (client == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client;
    }

//    
    public long update(Client client) {
        long status;

        try {
            controller.edit(client);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Client client) {
        long status;

        try {
            controller.destroy(client.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
}
