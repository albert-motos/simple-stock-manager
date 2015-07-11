package com.development.simplestockmanager.web.controller.edit;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.BrandCommonController;
import com.development.simplestockmanager.web.controller.common.EditController;
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
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.BRAND) + brand.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(brand);

            if (validator.validate()) {
                brand.setLastModifiedDate(new Date());
                brand.setLastModifiedUser(user);

                Long status = generalController.update(brand);

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.BRAND) + brand.getId()
                            + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.EDIT);
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.BRAND);
    }

}
