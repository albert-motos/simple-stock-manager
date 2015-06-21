/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.SexTypeHelper;
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class SexTypeSpecificController {
    
    private LanguageType languageType;

    public SexTypeSpecificController(LanguageType languageType) {
        this.languageType = languageType;
    }

    public List<SexType> fillSelector() {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = new SexTypeHelper().getFindAllForSelector(languageType);
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
