package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;

/**
 *
 * @author foxtrot
 */
public class Page {
    
    private final String brand;
    private final String client;
    private final String employee;
    private final String product;
    private final String provider;
    private final String store;
    
    private final String employee_type;
    private final String payment_type;
    private final String price_type;
    private final String product_type;
    private final String sex_type;
    
    private final String stock;
    
    public Page(String base) {
        brand = replace(base, CommonConstant.ENTITY.BRAND);
        client = replace(base, CommonConstant.ENTITY.CLIENT);
        employee = replace(base, CommonConstant.ENTITY.EMPLOYEE);
        product = replace(base, CommonConstant.ENTITY.PRODUCT);
        provider = replace(base, CommonConstant.ENTITY.PROVIDER);
        store = replace(base, CommonConstant.ENTITY.STORE);
        
        employee_type = replace(base, CommonConstant.TYPE.EMPLOYEE_TYPE);
        payment_type = replace(base, CommonConstant.TYPE.PAYMENT_TYPE);
        price_type = replace(base, CommonConstant.TYPE.PRICE_TYPE);
        product_type = replace(base, CommonConstant.TYPE.PRODUCT_TYPE);
        sex_type = replace(base, CommonConstant.TYPE.SEX_TYPE);
        
        stock = replace(base, CommonConstant.RELATION.STOCK);
    }
    
    private String replace(String base, String type) {
        LanguageController controller = LanguageControllerManager.getInstance().getController();

        if (base.contains(CommonConstant.VARIANT.ARTICLE.CODE)) {
            base = base.replace(CommonConstant.VARIANT.ARTICLE.CODE, controller.getWord(type + CommonConstant.VARIANT.ARTICLE.TEXT));
        } else if (base.contains(CommonConstant.VARIANT.GENERAL.CODE)) {
            base = base.replace(CommonConstant.VARIANT.GENERAL.CODE, controller.getWord(type + CommonConstant.VARIANT.GENERAL.TEXT));
        } else if (base.contains(CommonConstant.VARIANT.SPECIFIC.CODE)) {
            base = base.replace(CommonConstant.VARIANT.SPECIFIC.CODE, controller.getWord(type + CommonConstant.VARIANT.SPECIFIC.TEXT));
        }
        
        return base;
    }

    public String getBrand() {
        return brand;
    }

    public String getClient() {
        return client;
    }

    public String getEmployee() {
        return employee;
    }

    public String getProduct() {
        return product;
    }

    public String getProvider() {
        return provider;
    }

    public String getStore() {
        return store;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getPrice_type() {
        return price_type;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getSex_type() {
        return sex_type;
    }

    public String getStock() {
        return stock;
    }
    
}
