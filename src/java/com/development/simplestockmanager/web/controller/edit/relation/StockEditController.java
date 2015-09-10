package com.development.simplestockmanager.web.controller.edit.relation;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.relation.StockCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Stock object
 *
 * @author foxtrot
 */
@ManagedBean(name = "stockEdit")
@ViewScoped
public class StockEditController extends StockCommonController implements EditController {

    private Stock baseStock;
    private BigDecimal amount;

    public StockEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            stock = (Stock) receiveObjectFromSession(WebConstant.SESSION.STOCK);
            baseStock = new Stock(stock);
        } catch (Exception e) {
            back();
        }
        
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ENABLE, stock.getStore());
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.ENABLE, stock.getProduct());
        amount = BigDecimal.ZERO;
    }
    
    @Override
    public void edit() {
        stock.setStore(storeSelector.getSelectedValue());
        stock.setProduct(productSelector.getSelectedValue());
        
        if (stock.equals(baseStock)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.RELATION.STOCK, stock.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(stock);

            if (validator.validate()) {
                stock.setLastModifiedDate(new Date());
                stock.setLastModifiedUser(user);

                Long feedback = generalController.update(stock);

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.RELATION.STOCK, stock.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.RELATION.STOCK);
    }
    
}
