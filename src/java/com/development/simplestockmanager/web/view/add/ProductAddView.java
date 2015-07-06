package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.object.controller.general.ProductGeneralController;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.InternationalizationConstant;
import com.development.simplestockmanager.web.common.Constant;
import com.development.simplestockmanager.web.object.component.selector.BrandSelector;
import com.development.simplestockmanager.web.object.component.selector.ProviderSelector;
import com.development.simplestockmanager.web.object.component.selector.type.ProductTypeSelector;
import com.development.simplestockmanager.web.object.validator.ProductValidator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Add view controller class for Product object
 * @author foxtrot
 */
@ManagedBean(name = "productAdd")
@ViewScoped
public class ProductAddView extends BaseAddView {
    
    private final ProductValidator validator;
    private final ProductGeneralController generalController;
    
    private final BrandSelector brandSelector;
    private final ProviderSelector providerSelector;
    private final ProductTypeSelector productTypeSelector;
    private final Product product;

    public ProductAddView() {
        validator = new ProductValidator(Constant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new ProductGeneralController();
        
        product = new Product();
        brandSelector = new BrandSelector();
        providerSelector = new ProviderSelector();
        productTypeSelector = new ProductTypeSelector(internationalizationController.getLanguage());
    }

    @Override
    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();

        product.setBrand(brandSelector.getSelectedValue());
        product.setProvider(providerSelector.getSelectedValue());
        product.setProductType(productTypeSelector.getSelectedValue());
        validator.setObject(product);

        if (validator.validate()) {
            product.setCreatedDate(new Date());
            product.setLastModifiedDate(new Date());
            product.setCreatedUser(user.getUsername());
            product.setLastModifiedUser(user.getUsername());

            Long id = generalController.create(product);

            FacesMessage.Severity severity;
            String summary;
            String detail;

            if (id == Constant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.FATAL.DETAIL.DATABASE);
            } else {
                added = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.SUMMARY);
                detail = internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.OBJECT.PRODUCT) + id +
                        internationalizationController.getWord(InternationalizationConstant.MESSAGE.INFO.DETAIL.ACTION.CREATE);
            }

            context.addMessage(null, new FacesMessage(severity, summary, detail));

        } else {
            for (FacesMessage message : validator.getMessageList()) {
                context.addMessage(null, message);
            }
        }
    }

    public BrandSelector getBrandSelector() {
        return brandSelector;
    }

    public ProviderSelector getProviderSelector() {
        return providerSelector;
    }

    public ProductTypeSelector getProductTypeSelector() {
        return productTypeSelector;
    }

    public Product getProduct() {
        return product;
    }

}
