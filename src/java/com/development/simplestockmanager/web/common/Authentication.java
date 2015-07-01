/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.LanguageType;

/**
 *
 * @author foxtrot
 */
public class Authentication {
    
    public Employee getCurrentUser(){
        Employee e = new Employee(Long.valueOf(1));
        e.setUsername("Dummy");
        LanguageType languageType = new LanguageType();
        languageType.setId(Long.valueOf(1));
//        languageType.setCode("en_US");
        languageType.setCode("ca_ES");
//        languageType.setCode("es_ES");
        e.setLanguageType(languageType);
        
        return e;
    }
}
