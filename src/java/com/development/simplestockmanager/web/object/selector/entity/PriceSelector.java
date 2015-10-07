package com.development.simplestockmanager.web.object.selector.entity;

import com.development.simplestockmanager.common.web.object.selector.common.CommonSelector;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import com.development.simplestockmanager.business.object.controller.specific.PriceSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.PriceNull;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Selector class for Price object
 *
 * @author foxtrot
 */
public class PriceSelector extends CommonSelector implements BaseSelector {

    private final PriceSpecificController specificController;
    private HashMap<String, Price> hashMap;

    public PriceSelector(long mode) {
        super(mode);
        this.specificController = new PriceSpecificController();
    }

    public PriceSelector(long mode, Price price) {
        this(mode);
        this.selection = getDisplayName(price);
        this.browser = this.selection;
        search();
    }

    @Override
    public void search() {
        System.out.println("HERE on selector");
        clear();
        List<Price> priceList;

        if (mode == WebConstant.SELECTOR.MODE.RELATED) {
            String store = (String) receiveObjectFromSession(WebConstant.SESSION.STORE);
            String product = (String) receiveObjectFromSession(WebConstant.SESSION.PRODUCT);
            if (store != null && product != null) {
                priceList = specificController.findEnableByStock(store, product);
//                removeObjectFromSession(WebConstant.SESSION.STORE);
//                removeObjectFromSession(WebConstant.SESSION.PRODUCT);

                for (Price price : priceList) {
                    String key = getDisplayName(price);
                    hashMap.put(key, price);
                    list.add(key);
                }
            }

        }
//

    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    public Price getSelectedValue() {
        Price price = new PriceNull();

        if (!selection.isEmpty()) {
            price = hashMap.get(selection);
        }

        return price;
    }

    public String getDisplayName(Price price) {
        String name = "";

        if (price != null) {
            name = price.getTitle();
        }

        return name;
    }

    private Object receiveObjectFromSession(String key) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) currentInstance.getExternalContext().getRequest();
        return request.getSession().getAttribute(key);
    }

    private void removeObjectFromSession(String key) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) currentInstance.getExternalContext().getRequest();
        request.getSession().removeAttribute(key);
    }

}
