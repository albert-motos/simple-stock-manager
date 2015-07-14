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
public class SexTypeSpecificController {

    private final SexTypeHelper helper;

    public SexTypeSpecificController() {
        this.helper = new SexTypeHelper();
    }

    public List<SexType> getFindAll() {
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

    public List<SexType> getFindEnable() {
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
