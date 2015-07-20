package com.development.simplestockmanager.web.object.selector.type;

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
