/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.common.internationalization;

import com.development.simplestockmanager.common.InternationalizationConstant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author foxtrot
 */
public class InternationalizationController {
    
    private Map<String, Locale> supportedLanguages;
    private ResourceBundle translation;

    private InternationalizationController() {
        supportedLanguages = new HashMap();
        supportedLanguages.put("en_US", Locale.ENGLISH);
        supportedLanguages.put("es_ES", new Locale("es","ES"));
        supportedLanguages.put("ca_ES", new Locale("ca","ES"));
    }

    
    public InternationalizationController(String language){
        this();
        translation = ResourceBundle.getBundle(InternationalizationConstant.PATH, supportedLanguages.get(language));
        
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
