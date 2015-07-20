package com.development.simplestockmanager.web.controller.edit;

import com.development.simplestockmanager.business.common.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.CommonConstant;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import com.development.simplestockmanager.web.controller.common.ProductCommonController;
import com.development.simplestockmanager.web.controller.common.EditController;
import com.development.simplestockmanager.web.object.selector.BrandSelector;
import com.development.simplestockmanager.web.object.selector.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Product object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productEdit")
@ViewScoped
public class ProductEditController extends ProductCommonController implements EditController {

    private Product baseProduct;

    public ProductEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            product = (Product) receiveObjectFromSession(WebConstant.SESSION.PRODUCT);
            baseProduct = new Product(product);
        } catch (Exception e) {
            back();
        }
        
        productTypeSelector = new ProductTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, languageController.getLanguage(), product.getProductType());
        brandSelector = new BrandSelector(WebConstant.SELECTOR.MODE.ENABLE, product.getBrand());
        providerSelector = new ProviderSelector(WebConstant.SELECTOR.MODE.ENABLE, product.getProvider());
    }
    
    @Override
    public void edit() {
        product.setProductType(productTypeSelector.getSelectedValue());
        product.setBrand(brandSelector.getSelectedValue());
        product.setProvider(providerSelector.getSelectedValue());
        
        if (product.equals(baseProduct)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
            detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT) + product.getId()
                    + languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.ACTION.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(product);

            if (validator.validate()) {
                product.setLastModifiedDate(new Date());
                product.setLastModifiedUser(user);

                Long status = generalController.update(product);

                if (status == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = languageController.getWord(CommonConstant.MESSAGE.FATAL.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.FATAL.DETAIL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = languageController.getWord(CommonConstant.MESSAGE.INFO.SUMMARY);
                    detail = languageController.getWord(CommonConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT) + product.getId()
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
        new NavigationService().redirect(WebConstant.WEB.SEARCH.PRODUCT);
    }
    
}
