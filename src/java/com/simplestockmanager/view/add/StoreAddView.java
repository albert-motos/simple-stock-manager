/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.add;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.data.controller.general.StoreGeneralController;
import com.simplestockmanager.persistence.Employee;
import com.simplestockmanager.persistence.Store;
import com.simplestockmanager.view.selector.EmployeeSelectorView;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class StoreAddView implements AddView {
    
    private Store store;
    private boolean added;
    private Employee manager;

    public StoreAddView() {
        store = new Store();
        added = false;
    }

    @Override
    public void add() {
        System.out.println("#" + EmployeeSelectorView.getValue().getId());
        if (validate()) {
            long id = StoreGeneralController.create(store.getName(), store.getStreet(), store.getCity(), store.getState(), store.getCountry(), store.getPhone(),
                    1, store.getIsEnable(), new Date(), new Date());

            if (id == Constant.IDENTIFIER.INVALID) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create store right now"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Store [" + id + "] is created properly"));
                added = true;
            }
        }
    }

    @Override
    public boolean validate() {

        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String fields_empty = "";

        fields_empty = fields_empty.concat((store.getName().isEmpty() ? "Name " : ""));
        fields_empty = fields_empty.concat((store.getStreet().isEmpty() ? "Street " : ""));
        fields_empty = fields_empty.concat((store.getCity().isEmpty() ? "City " : ""));
        fields_empty = fields_empty.concat((store.getState().isEmpty() ? "State " : ""));
        fields_empty = fields_empty.concat((store.getCountry().isEmpty() ? "Country " : ""));
        fields_empty = fields_empty.concat((store.getPhone().isEmpty() ? "Phone " : ""));
        
        if (!fields_empty.isEmpty()) {
            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
        }

        return currentInstance.getMessageList().isEmpty();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

}
