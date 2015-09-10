package com.development.simplestockmanager.web.controller.add.relation;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.relation.StockCommonController;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for Stock object
 *
 * @author foxtrot
 */
@ManagedBean(name = "stockAdd")
@ViewScoped
public class StockAddController extends StockCommonController implements AddController {

    public StockAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        stock = new Stock();
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ENABLE);
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.ENABLE);
        
        stock.setActualAmount(BigDecimal.ZERO);
    }

    @Override
    public void add() {
        stock.setStore(storeSelector.getSelectedValue());
        stock.setProduct(productSelector.getSelectedValue());
        stock.setTotalAmount(stock.getActualAmount());
        validator.setObject(stock);

        if (validator.validate()) {
            stock.setCreatedDate(new Date());
            stock.setLastModifiedDate(new Date());
            stock.setCreatedUser(user);
            stock.setLastModifiedUser(user);

            Long id = generalController.create(stock);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.RELATION.STOCK, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
