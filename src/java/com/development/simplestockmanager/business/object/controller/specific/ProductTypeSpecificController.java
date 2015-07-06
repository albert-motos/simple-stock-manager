package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.ProductTypeHelper;
import com.development.simplestockmanager.business.persistence.ProductType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class ProductTypeSpecificController {

    private final ProductTypeHelper helper;
    private final String language;

    public ProductTypeSpecificController(String language) {
        this.language = language;
        helper = new ProductTypeHelper();
    }

    public List<ProductType> fillSelector() {
        List<ProductType> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((ProductType) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
