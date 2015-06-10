/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.BrandHelper;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class BrandSpecificController {

    static public boolean nameIsAvailable(String name) {
        Query query = BrandHelper.getFindByNameQuery(name);

        return query.getResultList().isEmpty();
    }
}
