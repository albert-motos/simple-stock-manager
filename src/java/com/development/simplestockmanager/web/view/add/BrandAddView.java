
package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.business.object.controller.general.BrandGeneralController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.object.validator.BrandValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandAdd")
@ViewScoped
public class BrandAddView extends BaseAddView {

    private final BrandValidator validator;
    private final BrandGeneralController generalController;

    private final Brand brand;

    public BrandAddView() {
        validator = new BrandValidator(WebConstant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new BrandGeneralController();

        brand = new Brand();
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();

        validator.setObject(brand);

        if (validator.validate()) {
            brand.setCreatedDate(new Date());
            brand.setLastModifiedDate(new Date());
            brand.setCreatedUser(user);
            brand.setLastModifiedUser(user);

            Long id = generalController.create(brand);

            Severity severity;
            String summary;
            String detail;

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.BRAND) + id +
                        internationalizationController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public Brand getBrand() {
        return brand;
    }

}
