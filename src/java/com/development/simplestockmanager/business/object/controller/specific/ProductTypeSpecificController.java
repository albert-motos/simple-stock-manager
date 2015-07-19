package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductTypeHelper;
import com.development.simplestockmanager.business.persistence.ProductType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for ProductType object
 *
 * @author foxtrot
 */
public class ProductTypeSpecificController {

    private final ProductTypeHelper helper;

    public ProductTypeSpecificController() {
        this.helper = new ProductTypeHelper();
    }

    public List<ProductType> findAll() {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAll();
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<ProductType>  findEnable() {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindEnable();
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
