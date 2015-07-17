package com.development.simplestockmanager.web.object.component.selector;

import com.development.simplestockmanager.business.object.controller.specific.ClientSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ClientNull;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.web.common.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Client object
 *
 * @author foxtrot
 */
public class ClientSelector extends CommonSelector implements BaseSelector {

    private final ClientSpecificController specificController;
    private HashMap<String, Client> hashMap;

    public ClientSelector(long mode) {
        super(mode);
        this.specificController = new ClientSpecificController();
        search();
    }

    public ClientSelector(long mode, Client client) {
        this(mode);
        this.selection = getDisplayName(client);
    }

    @Override
    public void search() {
        clear();
        List<Client> clientList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            clientList = specificController.findAllByBrowser(browser);
        } else {
            clientList = specificController.findEnableByBrowser(browser);
        }

        for (Client client : clientList) {
            String key = getDisplayName(client);
            hashMap.put(key, client);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public Client getSelectedValue() {
        Client client = new ClientNull();

        if (!selection.isEmpty()) {
            client = hashMap.get(selection);
        }

        return client;
    }
    
    public String getDisplayName(Client client) {
        String name = "";
        
        if (client != null) {
            name = client.getLastname() + ", " + client.getFirstname();
        }
        
        return name;
    }

}
