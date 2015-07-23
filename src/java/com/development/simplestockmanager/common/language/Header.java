/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Header(LanguageController controller, String type) {
        this.title = controller.getWord(type);
        this.create = controller.getWord(CommonConstant.TRANSLATION.HEADER.CREATE) + " " + this.title.toLowerCase();
        this.search = controller.getWord(CommonConstant.TRANSLATION.HEADER.SEARCH) + " " + this.title.toLowerCase();
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
