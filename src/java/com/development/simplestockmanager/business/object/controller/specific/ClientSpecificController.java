package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ClientHelper;
import com.development.simplestockmanager.business.persistence.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Client object
 * @author foxtrot
 */
public class ClientSpecificController {
    
    private final ClientHelper helper;

    public ClientSpecificController() {
        helper = new ClientHelper();
    }
    
    public List<Client> findAllForBrowser(Client browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Client> list = new ArrayList<>();

        try {
            Query query = helper.getFindForBrowserQuery(browser.getFirstname(), browser.getLastname(), browser.getSexType().getId(), browser.getBornDate(),
                    browser.getPhone(), browser.getEmail(), status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, 
                    lastModifiedUserID);
            for (Object object : query.getResultList()) {
                list.add((Client) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
