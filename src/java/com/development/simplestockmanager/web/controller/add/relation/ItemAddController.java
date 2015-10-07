package com.development.simplestockmanager.web.controller.add.relation;

import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.relation.ItemCommonController;
import com.development.simplestockmanager.web.object.selector.entity.PriceSelector;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
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
    
    BigDecimal amount_max;
    BigDecimal amount;
    
    public ItemAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        list = new ArrayList<>();
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ENABLE);
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.RELATED);
        priceSelector = new PriceSelector(WebConstant.SELECTOR.MODE.RELATED);
        amount = BigDecimal.ZERO;
    }

    @Override
    public void add() {
        System.out.println("here" + storeSelector.getSelectedValue());
      
//        stock.setStore(storeSelector.getSelectedValue());
//        stock.setProduct(productSelector.getSelectedValue());
//        stock.setTotalAmount(stock.getActualAmount());
//        validator.setObject(stock);
//
//        if (validator.validate()) {
//            stock.setCreatedDate(new Date());
//            stock.setLastModifiedDate(new Date());
//            stock.setCreatedUser(user);
//            stock.setLastModifiedUser(user);
//
//            Long id = generalController.create(stock);
//
//            if (id == BusinessConstant.IDENTIFIER.INVALID) {
//                severity = FacesMessage.SEVERITY_FATAL;
//                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
//                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
//            } else {
//                action = true;
//                severity = FacesMessage.SEVERITY_INFO;
//                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
//                detail = messageService.getDetail(CommonConstant.RELATION.STOCK, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
//            }
//
//            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
//        } else {
//            for (FacesMessage message : validator.getMessageList()) {
//                getContext().addMessage(null, message);
//            }
//        }
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

}
