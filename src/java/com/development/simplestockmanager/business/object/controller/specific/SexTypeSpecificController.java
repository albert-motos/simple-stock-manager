package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.SexTypeHelper;
import com.development.simplestockmanager.business.persistence.SexType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for SexType object
 *
 * @author foxtrot
 */
public class SexTypeTypeSpecificController {

    private final SexTypeHelper helper;

    public SexTypeTypeSpecificController() {
        this.helper = new SexTypeHelper();
    }
    
    public List<SexType> findByType(String type) {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = helper.getFindByType(type);
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<SexType> findAll() {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<SexType> findEnable() {
        List<SexType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((SexType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
