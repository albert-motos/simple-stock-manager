package com.development.simplestockmanager.web.object.component.selector.type;

import java.util.List;

/**
 * Base class for type selector object
 *
 * @author foxtrot
 */
abstract class BaseTypeSelector {

    protected List<String> list;
    protected String selection;
    protected long mode;

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
