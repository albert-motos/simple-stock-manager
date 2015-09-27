package com.development.simplestockmanager.web.controller.edit.relation;

import com.development.simplestockmanager.business.object.controller.general.AnalyticsTimeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.RecordGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.RecordSpecificController;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.relation.StockCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.web.object.selector.entity.ProductSelector;
import com.development.simplestockmanager.web.object.selector.entity.StoreSelector;
import com.development.simplestockmanager.web.object.validator.relation.RecordValidator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
    
    private Record record;
    private RecordValidator recordValidator;
    private RecordGeneralController recordGeneralController;
    
    private AnalyticsTimeGeneralController analyticsTimeGeneralController;

    public StockEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            stock = (Stock) receiveObjectFromSession(WebConstant.SESSION.STOCK);
            baseStock = new Stock(stock);
        } catch (Exception e) {
            back();
        }
        
        recordValidator = new RecordValidator(WebConstant.VALIDATOR.MODE.EDIT, new RecordSpecificController());
        recordGeneralController = new RecordGeneralController();
        
        analyticsTimeGeneralController = new AnalyticsTimeGeneralController();
        
        storeSelector = new StoreSelector(WebConstant.SELECTOR.MODE.ENABLE, stock.getStore());
        productSelector = new ProductSelector(WebConstant.SELECTOR.MODE.ENABLE, stock.getProduct());
        record = new Record();
        record.setAmount(BigDecimal.ZERO);
    }
    
    @Override
    public void edit() {
        stock.setStore(storeSelector.getSelectedValue());
        stock.setProduct(productSelector.getSelectedValue());
        stock.setActualAmount(stock.getActualAmount().add(record.getAmount()));
        stock.setTotalAmount(stock.getTotalAmount().add(record.getAmount()));
        
        if (stock.equals(baseStock) && record.getAmount().equals(BigDecimal.ZERO)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.RELATION.STOCK, stock.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(stock);
            recordValidator.setObject(record);

            if (validator.validate() && recordValidator.validate()) {
                stock.setLastModifiedDate(new Date());
                stock.setLastModifiedUser(user);

                try {
                    Long feedback = generalController.update(stock);
                    
                    if (feedback == BusinessConstant.UPDATE.FAILURE) {
                        throw new Exception();
                    }
                    
                    record.setEmployee(user);
                    record.setCreatedUser(user);
                    record.setLastModifiedUser(user);
                    record.setEnable(true);
                    record.setCreatedDate(new Date());
                    record.setLastModifiedDate(new Date());
                    record.setStock(stock);
 
                    AnalyticsTime analyticsTime = new AnalyticsTime(new Date(), user);
                    analyticsTimeGeneralController.create(analyticsTime);
                    record.setAnalitycsTime(analyticsTime);
                    
                    feedback = recordGeneralController.create(record);
                    
                    if (feedback == BusinessConstant.IDENTIFIER.INVALID) {
                        generalController.delete(stock);
                        throw new Exception();
                    }
                    
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.RELATION.STOCK, stock.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                } catch (Exception e) {
                    System.out.println(e);
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                List<FacesMessage> messageList = recordValidator.getMessageList();
                messageList.addAll(validator.getMessageList());
                
                for (FacesMessage message : messageList) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }

    public Record getRecord() {
        return record;
    }

    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.RELATION.STOCK);
    }
    
}
