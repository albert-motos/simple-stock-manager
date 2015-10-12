package com.development.simplestockmanager.web.controller.add.relation;

import com.development.simplestockmanager.business.object.controller.general.AnalyticsTimeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.ItemGeneralController;
import com.development.simplestockmanager.business.object.controller.general.PriceGeneralController;
import com.development.simplestockmanager.business.object.controller.general.StockGeneralController;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.relation.InvoiceCommonController;
import com.development.simplestockmanager.web.object.selector.entity.ClientSelector;
import com.development.simplestockmanager.web.object.selector.type.PaymentTypeSelector;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for Invoice object
 *
 * @author foxtrot
 */
@ManagedBean(name = "invoiceAdd")
@ViewScoped
public class InvoiceAddController extends InvoiceCommonController implements AddController {

    private List<Item> items;
    private final AnalyticsTimeGeneralController analyticsTimeGeneralController;
    private final ItemGeneralController itemGeneralController;
    private final PriceGeneralController priceGeneralController;
    private final StockGeneralController stockGeneralController;

    public InvoiceAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);

        try {
            items = (List<Item>) receiveObjectFromSession(WebConstant.SESSION.ITEM);
        } catch (Exception e) {
            back();
        }

        invoice = new Invoice();
        analyticsTimeGeneralController = new AnalyticsTimeGeneralController();
        itemGeneralController = new ItemGeneralController();
        priceGeneralController = new PriceGeneralController();
        stockGeneralController = new StockGeneralController();
        
        clientSelector = new ClientSelector(WebConstant.SELECTOR.MODE.ENABLE);
        paymentTypeSelector = new PaymentTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
        invoice.setCost(calculateCost());
    }

    @Override
    public void add() {
        invoice.setClient(clientSelector.getSelectedValue());
        invoice.setPaymentType(getPaymentTypeSelector().getSelectedValue());
        invoice.setEnable(true);
        validator.setObject(invoice);

        if (validator.validate()) {
            Date date = new Date();
            AnalyticsTime analyticsTime = new AnalyticsTime(date, user);
            analyticsTimeGeneralController.create(analyticsTime);
            invoice.setAnalitycsTime(analyticsTime);
            invoice.setEmployee(user);
            invoice.setLastModifiedDate(date);
            invoice.setCreatedDate(date);
            invoice.setLastModifiedUser(user);
            invoice.setCreatedUser(user);

            Long id = generalController.create(invoice);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                assignItems2Invoice(invoice, items);
                
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.RELATION.INVOICE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }
        
    private BigDecimal calculateCost() {
        BigDecimal cost = BigDecimal.ZERO;

        for (Item item : items) {
            cost = cost.add(item.getAmount().multiply(item.getPrice().getCost()));
        }

        return cost;
    }

    private void assignItems2Invoice(Invoice invoice, List<Item> list) {
        Date date = new Date();
        
        for (Item item : list) {
            Price price = item.getPrice();
            price.setLastModifiedDate(date);
            price.setLastModifiedUser(user);
            priceGeneralController.update(price);
            
            Stock stock = item.getStock();
            stock.setLastModifiedDate(date);
            stock.setLastModifiedUser(user);
            stockGeneralController.update(stock);   
            
            item.setCreatedDate(date);
            item.setLastModifiedDate(date);
            item.setCreatedUser(user);
            item.setLastModifiedUser(user);
            item.setInvoice(invoice);
            itemGeneralController.create(item);
        }
    }

}