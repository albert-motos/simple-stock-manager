/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.component.translator;

import com.development.simplestockmanager.common.Constant;
import com.development.simplestockmanager.common.language.LanguageController;

/**
 *
 * @author foxtrot
 */
public class ClientTranslator {
    
    private LanguageController controller;

    public ClientTranslator(String language) {
        controller = new LanguageController(language);
        
    }

    public String getHeaderAttributes() {
//        return controller.getWord(Constant.LANGUAGE.CLIENT.HEADER.ATTRIBUTES);
        return controller.getWord("client.header.attributes");
    }
    
    
}
