package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.PriceCommonController;
import com.development.simplestockmanager.web.object.selector.entity.StockSelector;
import com.development.simplestockmanager.web.object.selector.type.PriceTypeSelector;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for Price object
 *
 * @author foxtrot
 */
@ManagedBean(name = "priceAdd")
@ViewScoped
public class PriceAddController extends PriceCommonController implements AddController {
    
    public PriceAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        
        stockSelector = new StockSelector(WebConstant.SELECTOR.MODE.ENABLE);
        priceTypeSelector = new PriceTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
        
        price = new Price();
        price.setCost(BigDecimal.ZERO);
        price.setInitialAmount(BigDecimal.ZERO);
    }

    @Override
    public void add() {
        price.setStock(stockSelector.getSelectedValue());
        price.setPriceType(priceTypeSelector.getSelectedValue());
        price.setEndAmount(price.getInitialAmount());
        validator.setObject(price);

        if (validator.validate()) {
            price.setCreatedDate(new Date());
            price.setLastModifiedDate(new Date());
            price.setCreatedUser(user);
            price.setLastModifiedUser(user);

            Long id = generalController.create(price);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.ENTITY.PRICE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
