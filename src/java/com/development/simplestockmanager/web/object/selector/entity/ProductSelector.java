package com.development.simplestockmanager.web.object.selector.entity;

import com.development.simplestockmanager.common.web.object.selector.common.CommonSelector;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import com.development.simplestockmanager.business.object.controller.specific.ProductSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.ProductNull;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Selector class for Product object
 *
 * @author foxtrot
 */
public class ProductSelector extends CommonSelector implements BaseSelector {

    private final ProductSpecificController specificController;
    private HashMap<String, Product> hashMap;

    public ProductSelector(long mode) {
        super(mode);
        this.specificController = new ProductSpecificController();
    }

    public ProductSelector(long mode, Product product) {
        this(mode);
        this.selection = getDisplayName(product);
        this.browser = this.selection;
        search();
    }

    @Override
    public void search() {
        clear();
        List<Product> productList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            productList = specificController.findAllByBrowser(browser);
        } else {
            if (mode == WebConstant.SELECTOR.MODE.RELATED) {
                String store = (String) receiveObjectFromSession(WebConstant.SESSION.STORE);
                productList = specificController.findEnableByStore(browser, store);
            } else {
                productList = specificController.findEnableByBrowser(browser);
            }
        }

        for (Product product : productList) {
            String key = getDisplayName(product);
            hashMap.put(key, product);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    public Product getSelectedValue() {
        Product product = new ProductNull();

        if (!selection.isEmpty()) {
            product = hashMap.get(selection);
        }

        return product;
    }

    public String getDisplayName(Product product) {
        String name = "";

        if (product != null) {
            name = product.getName();
        }

        return name;
    }

    private Object receiveObjectFromSession(String key) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) currentInstance.getExternalContext().getRequest();
        return request.getSession().getAttribute(key);
    }

}
