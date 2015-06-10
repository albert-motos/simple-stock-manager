/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.add;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.controller.general.ProductGeneralController;
import com.development.simplestockmanager.business.persistence.Product;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@ViewScoped
public class ProductAddView implements AddView {
    
    private Product product;
    private boolean added;

    public ProductAddView() {
        product = new Product();
        added = false;
    }

    @Override
    public void add() {
        if (validate()) {
            long id = ProductGeneralController.create(product.getName(), product.getDescription(), product.getIsEnable(), new Date(), new Date());

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create product right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String fields_empty = "";

        fields_empty = fields_empty.concat((product.getName().isEmpty() ? "Name " : ""));
        fields_empty = fields_empty.concat((product.getDescription().isEmpty() ? "Description " : ""));
        
        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

}
