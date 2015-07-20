package com.development.simplestockmanager.web.controller.add;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.controller.common.AddController;
import com.development.simplestockmanager.web.controller.common.ProductCommonController;
import com.development.simplestockmanager.web.object.selector.BrandSelector;
import com.development.simplestockmanager.web.object.selector.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Product object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productAdd")
@ViewScoped
public class ProductAddController extends ProductCommonController implements AddController {

    public ProductAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        product = new Product();
        productTypeSelector = new ProductTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage());
        brandSelector = new BrandSelector(WebConstant.SELECTOR.MODE.ENABLE);
        providerSelector = new ProviderSelector(WebConstant.SELECTOR.MODE.ENABLE);
    }

    @Override
    public void add() {
        product.setProductType(productTypeSelector.getSelectedValue());
        product.setBrand(brandSelector.getSelectedValue());
        product.setProvider(providerSelector.getSelectedValue());
        validator.setObject(product);
        
        if (validator.validate()) {
            product.setCreatedDate(new Date());
            product.setLastModifiedDate(new Date());
            product.setCreatedUser(user);
            product.setLastModifiedUser(user);

            Long id = generalController.create(product);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT) + id +
                        languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}
