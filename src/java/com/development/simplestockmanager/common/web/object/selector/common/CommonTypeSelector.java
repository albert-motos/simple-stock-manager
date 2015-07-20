package com.development.simplestockmanager.common.web.object.selector.common;

import java.util.List;

/**
 * Common class for type selectors
 *
 * @author foxtrot
 */
public class CommonTypeSelector {

    protected List<String> list;
    protected String selection;
    protected long mode;
    protected String language;

    public CommonTypeSelector(long mode, String language) {
        this.mode = mode;
        this.language = language;
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
