package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.WebConstant;

/**
 * Service class for button internationalization functionality
 *
 * @author foxtrot
 */

public class Value {

    private final long hidden;
    private final long visible;
    private final long indeterminate;

    public Value() {
        hidden = WebConstant.STATUS.HIDDEN;
        visible = WebConstant.STATUS.VISIBLE;
        indeterminate = WebConstant.STATUS.INDETERMINATE;
    }

    public long getHidden() {
        return hidden;
    }

    public long getVisible() {
        return visible;
    }

    public long getIndeterminate() {
        return indeterminate;
    }
    
}
