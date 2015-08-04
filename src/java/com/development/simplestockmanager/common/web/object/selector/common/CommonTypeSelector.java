package com.development.simplestockmanager.common.web.object.selector.common;

import com.development.simplestockmanager.common.language.LanguageController;
import com.development.simplestockmanager.common.language.LanguageControllerManager;
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

    public CommonTypeSelector(long mode) {
        LanguageController languageController = LanguageControllerManager.getInstance().getController();
        this.mode = mode;
        this.language = languageController.getLanguage();
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
