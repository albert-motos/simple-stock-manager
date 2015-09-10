package com.development.simplestockmanager.web.controller;

import com.development.simplestockmanager.business.object.controller.specific.RecordSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.RecordNull;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "internal")
@ViewScoped
public class InternalController extends BaseCommonController {

    private final RecordSpecificController recordSpecificController;
    
    private Record browser;
    private Record viewer;
    private List<Record> list;

    public InternalController() {
        recordSpecificController = new RecordSpecificController();
        clear();
    }

    public void search() {
        list = recordSpecificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    public final void clear() {
        browser = new RecordNull();
        list = new ArrayList<>();
        
        super.clear();
    }

    public void initView(Record record) {
        this.viewer = record;
    }

    public Record getBrowser() {
        return browser;
    }

    public Record getViewer() {
        return viewer;
    }

    public List<Record> getList() {
        return list;
    }

}
