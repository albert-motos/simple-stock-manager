/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.specific;

import com.simplestockmanager.data.nullpackage.AnalyticsTimeNull;
import com.simplestockmanager.helper.AnalyticsTimeHelper;
import com.simplestockmanager.helper.SexTypeHelper;
import com.simplestockmanager.persistence.AnalyticsTime;
import com.simplestockmanager.persistence.SexType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class SexTypeSpecificController {

    static public List<SexType> getAll() {

        List<SexType> list = new ArrayList<>();

        try {
            Query query = SexTypeHelper.getAllQuery();

            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
