package com.development.simplestockmanager.web.object.component.selector;

import java.util.List;

/**
 *
 * @author foxtrot
 */
abstract class BaseSelector {
    
    protected String browser;
    protected List<String> list;
    protected String selection;
    
    abstract public void find();

    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    public String getBrowser() {
        return browser;
    }

    public List<String> getList() {
        return list;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
    
}
