package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;

/**
 *
 * @author foxtrot
 */
public class Header {
    
    private final String title;
    private final String create;
    private final String search;

    public Header(String type) {
//        LanguageController controller = LanguageControllerManager.getInstance().getController();
        LanguageController controller = new LanguageController("es_ES");
        this.title = controller.getWord(type);
        this.create = controller.getWord(CommonConstant.HEADER.CREATE) + " " + this.title.toLowerCase();
        this.search = controller.getWord(CommonConstant.HEADER.SEARCH) + " " + this.title.toLowerCase();
    }

    public String getTitle() {
        return title;
    }

    public String getCreate() {
        return create;
    }

    public String getSearch() {
        return search;
    }
    
}
