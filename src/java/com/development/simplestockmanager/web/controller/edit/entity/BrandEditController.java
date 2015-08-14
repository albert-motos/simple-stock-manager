package com.development.simplestockmanager.web.controller.edit.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.BrandCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandEdit")
@ViewScoped
public class BrandEditController extends BrandCommonController implements EditController {
    
    private Brand baseBrand;
    
    public BrandEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            brand = (Brand) receiveObjectFromSession(WebConstant.SESSION.BRAND);
            baseBrand = new Brand(brand);
        } catch (Exception e) {
            back();
        }
    }

    @Override
    public void edit() {
        if (brand.equals(baseBrand)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.ENTITY.BRAND, brand.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(brand);

            if (validator.validate()) {
                brand.setLastModifiedDate(new Date());
                brand.setLastModifiedUser(user);

                Long feedback = generalController.update(brand);

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.ENTITY.BRAND, brand.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }

    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.ENTITY.BRAND);
    }

}
