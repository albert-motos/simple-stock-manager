package com.development.simplestockmanager.web.controller.add.relation;

import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.relation.ItemCommonController;
import com.development.simplestockmanager.web.object.selector.entity.PriceSelector;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.service.general.NavigationService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 * Add controller class for Invoice object
 *
 * @author foxtrot
 */
@ManagedBean(name = "itemAdd")
@ViewScoped
public class ItemAddController extends ItemCommonController implements AddController {
    
    boolean visible;
    BigDecimal amount_max;
    BigDecimal amount;
    
    public ItemAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        list = new ArrayList<>();
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ENABLE);
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.RELATED);
        priceSelector = new PriceSelector(WebConstant.SELECTOR.MODE.RELATED);
        amount = BigDecimal.ZERO;
        visible = false;
    }

    @Override
    public void add() {
        Stock stock = specificController.findByProductAndStore(productSelector.getSelectedValue(), storeSelector.getSelectedValue());
        stock.setActualAmount(stock.getActualAmount().add(amount.negate()));
        
        Price price = priceSelector.getSelectedValue();
        price.setEndAmount(price.getEndAmount().add(amount.negate()));
        
        Item item = new Item();
        item.setAmount(amount);
        item.setPrice(price);
        item.setStock(stock);
        
        list.add(0, item);
        if (!list.isEmpty()) {
            visible = true;
        }
        
        productSelector.clear();
        priceSelector.clear();
    }
    
    public void finish() {
        sendObjectToSession(WebConstant.SESSION.ITEM, list);
        new NavigationService().redirect(WebConstant.WEB.ADD.RELATION.INVOICE);
    }
    
    public void remove(Item item) {
        list.remove(item);
    }
    
    public void storeListener(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            sendObjectToSession(WebConstant.SESSION.STORE, event.getNewValue());
        }
    }
    
    public void productListener(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            sendObjectToSession(WebConstant.SESSION.PRODUCT, event.getNewValue());
        }
    }
    
    public void priceListener() {
        amount = BigDecimal.ZERO;
        amount_max = priceSelector.getSelectedValue().getEndAmount();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getAmount_max() {
        return amount_max;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isVisible() {
        return visible;
    }

}
