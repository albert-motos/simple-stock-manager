package com.development.simplestockmanager.web.object.component.selector;

import java.util.List;

/**
 * Common class for selectors
 *
 * @author foxtrot
 */
public class CommonSelector {

    protected String browser;
    protected List<String> list;
    protected String selection;
    protected long mode;
    protected String language;

    public CommonSelector(long mode, String language) {
        this.mode = mode;
        this.language = language;
    }
    
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
