package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.RecordHelper;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.Record;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Provider object
 *
 * @author foxtrot
 */
public class RecordSpecificController {

    private final RecordHelper helper;

    public RecordSpecificController() {
        helper = new RecordHelper();
    }

    public List<Record> findAllForBrowser(Record browser, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo,
            long createdUserID, long lastModifiedUserID) {
        List<Record> list = new ArrayList<>();

        try {
//            Query query = helper.getFindForBrowserQuery(browser.getEmail(), browser.getIdentifier(), browser.getName(), browser.getPhone(), status,
//                    createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
//            for (Object object : query.getResultList()) {
//                list.add((Record) object);
//            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
 
}
