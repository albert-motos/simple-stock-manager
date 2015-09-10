package com.development.simplestockmanager.web.service.general;

import com.development.simplestockmanager.common.constant.WebConstant;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Service class for url functionality
 *
 * @author foxtrot
 */
@ManagedBean(name = "url")
@ApplicationScoped
public class UrlService implements Serializable {

    private final HashMap<String, String> addHashMap;
    private final HashMap<String, String> searchHashMap;
    
    private final String internal;
    private final String external;

    public UrlService() {
        addHashMap = new HashMap<>();
        searchHashMap = new HashMap<>();
        
        internal = WebConstant.WEB.RELATION.INTERNAL;
        external = WebConstant.WEB.RELATION.EXTERNAL;

        initilazation();
    }

    public String add(String destine) {
        String url = addHashMap.get(destine);

        return (url == null ? WebConstant.WEB.INDEX : url);
    }

    public String search(String destine) {
        String url = searchHashMap.get(destine);

        return (url == null ? WebConstant.WEB.INDEX : url);
    }

    private void initilazation() {
        addHashMap.put("brand", WebConstant.WEB.ADD.ENTITY.BRAND);
        addHashMap.put("client", WebConstant.WEB.ADD.ENTITY.CLIENT);
        addHashMap.put("employee", WebConstant.WEB.ADD.ENTITY.EMPLOYEE);
        addHashMap.put("product", WebConstant.WEB.ADD.ENTITY.PRODUCT);
        addHashMap.put("provider", WebConstant.WEB.ADD.ENTITY.PROVIDER);
        addHashMap.put("store", WebConstant.WEB.ADD.ENTITY.STORE);

        addHashMap.put("employee_type", WebConstant.WEB.ADD.TYPE.EMPLOYEE_TYPE);
        addHashMap.put("payment_type", WebConstant.WEB.ADD.TYPE.PAYMENT_TYPE);
        addHashMap.put("price_type", WebConstant.WEB.ADD.TYPE.PRICE_TYPE);
        addHashMap.put("product_type", WebConstant.WEB.ADD.TYPE.PRODUCT_TYPE);
        addHashMap.put("sex_type", WebConstant.WEB.ADD.TYPE.SEX_TYPE);
        
        addHashMap.put("stock", WebConstant.WEB.ADD.RELATION.STOCK);

        searchHashMap.put("brand", WebConstant.WEB.SEARCH.ENTITY.BRAND);
        searchHashMap.put("client", WebConstant.WEB.SEARCH.ENTITY.CLIENT);
        searchHashMap.put("employee", WebConstant.WEB.SEARCH.ENTITY.EMPLOYEE);
        searchHashMap.put("product", WebConstant.WEB.SEARCH.ENTITY.PRODUCT);
        searchHashMap.put("provider", WebConstant.WEB.SEARCH.ENTITY.PROVIDER);
        searchHashMap.put("store", WebConstant.WEB.SEARCH.ENTITY.STORE);

        searchHashMap.put("employee_type", WebConstant.WEB.SEARCH.TYPE.EMPLOYEE_TYPE);
        searchHashMap.put("payment_type", WebConstant.WEB.SEARCH.TYPE.PAYMENT_TYPE);
        searchHashMap.put("price_type", WebConstant.WEB.SEARCH.TYPE.PRICE_TYPE);
        searchHashMap.put("product_type", WebConstant.WEB.SEARCH.TYPE.PRODUCT_TYPE);
        searchHashMap.put("sex_type", WebConstant.WEB.SEARCH.TYPE.SEX_TYPE);
        
        searchHashMap.put("stock", WebConstant.WEB.SEARCH.RELATION.STOCK);
    }

    public String getInternal() {
        return internal;
    }

    public String getExternal() {
        return external;
    }

}
