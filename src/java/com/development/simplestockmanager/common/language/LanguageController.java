/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.Constant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author foxtrot
 */
public class LanguageController {
    
    private Map<String, Locale> supportedLanguages;
    private ResourceBundle translation;

    public LanguageController() {
        supportedLanguages = new HashMap();
        supportedLanguages.put("en_US", Locale.ENGLISH);
        supportedLanguages.put("es_ES", new Locale("es","ES"));
        supportedLanguages.put("ca_ES", new Locale("ca","ES"));
    }

    
    public LanguageController(String language){
        this();
        translation = ResourceBundle.getBundle(Constant.LANGUAGE.PATH, supportedLanguages.get(language));
        
    }

    public String getWord(String keyword)
    {
        try {
            return translation.getString(keyword);
        } catch (Exception e) {
            return "Undefined";
        }
        
    }

}
