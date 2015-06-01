/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.add;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.controller.general.BrandGeneralController;
import com.simplestockmanager.data.controller.general.ProductGeneralController;
import com.simplestockmanager.data.controller.general.StoreGeneralController;
import com.simplestockmanager.data.controller.specific.BrandSpecificController;
import com.simplestockmanager.persistence.Brand;
import com.simplestockmanager.persistence.Product;
import com.simplestockmanager.persistence.Store;
import com.simplestockmanager.view.selector.EmployeeSelectorView;
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
public class BrandAddView implements AddView {

    private Brand brand;
    private boolean added;

    public BrandAddView() {
        brand = new Brand();
        added = false;
    }

    @Override
    public void add() {
        if (validate()) {
            long id = BrandGeneralController.create(brand.getName(), brand.getIsEnable());

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create brand right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Brand [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String fields_empty = "";

        fields_empty = fields_empty.concat((brand.getName().isEmpty() ? "Name" : ""));

        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        if (!brand.getName().isEmpty()) {
            if (!BrandSpecificController.nameIsAvailable(brand.getName())) {
                currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The name is already in use, change it"));
            }
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

}
