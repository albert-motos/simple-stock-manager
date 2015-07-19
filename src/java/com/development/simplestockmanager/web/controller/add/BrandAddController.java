package com.development.simplestockmanager.web.controller.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.BrandCommonController;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Add controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandAdd")
@ViewScoped
public class BrandAddController extends BrandCommonController implements AddController {

    public BrandAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        brand = new Brand();
    }

    @Override
    public void add() {
        validator.setObject(brand);

        if (validator.validate()) {
            brand.setCreatedDate(new Date());
            brand.setLastModifiedDate(new Date());
            brand.setCreatedUser(user);
            brand.setLastModifiedUser(user);

            Long id = generalController.create(brand);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.BRAND) + id
                        + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
