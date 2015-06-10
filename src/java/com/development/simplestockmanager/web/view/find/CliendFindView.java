/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.find;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@ApplicationScoped
public class CliendFindView implements FindView {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CliendFindView() {
        System.out.println("#INIT");
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        Cookie cookie = new Cookie("key", "value");
        response.addCookie(cookie);
        
    }

    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        context.getExternalContext().invalidateSession();
//        req
//        request.setAttribute("key", "value");
//
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleStockManager/find/client.xhtml");
//        } catch (IOException ex) {
//            Logger.getLogger(CliendFindView.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    private Store store;
//    private boolean added;
//    private EmployeeSelectorView employeeSelectorView;
//
//    public CliendFindView() {
//        store = new Store();
//        added = false;
//        employeeSelectorView = new EmployeeSelectorView();
//    }
//
//    @Override
//    public void add() {
//        if (validate()) {
//            long id = StoreGeneralController.create(store.getName(), store.getStreet(), store.getCity(), store.getState(), store.getCountry(), store.getPhone(),
//                    employeeSelectorView.getSelectedValue().getId(), store.getIsEnable(), new Date(), new Date());
//
//            if (id == Constant.IDENTIFIER.INVALID) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "You can not create store right now"));
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Store [" + id + "] is created properly"));
//                added = true;
//            }
//        }
//    }
//
//    @Override
//    public boolean validate() {
//
//        FacesContext currentInstance = FacesContext.getCurrentInstance();
//        String fields_empty = "";
//
//        fields_empty = fields_empty.concat((store.getName().isEmpty() ? "Name " : ""));
//        fields_empty = fields_empty.concat((store.getStreet().isEmpty() ? "Street " : ""));
//        fields_empty = fields_empty.concat((store.getCity().isEmpty() ? "City " : ""));
//        fields_empty = fields_empty.concat((store.getState().isEmpty() ? "State " : ""));
//        fields_empty = fields_empty.concat((store.getCountry().isEmpty() ? "Country " : ""));
//        fields_empty = fields_empty.concat((store.getPhone().isEmpty() ? "Phone " : ""));
//        
//        if (!fields_empty.isEmpty()) {
//            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The next field/s couldn't be empty: " + fields_empty));
//        }
//        
//        if (employeeSelectorView.getSelectedValue().getId() == Constant.IDENTIFIER.INVALID) {
//            currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "The employee_selector is not indicated"));
//        }
//
//        return currentInstance.getMessageList().isEmpty();
//    }
//
//    public Store getStore() {
//        return store;
//    }
//
//    public void setStore(Store store) {
//        this.store = store;
//    }
//
//    public boolean isAdded() {
//        return added;
//    }
//
//    public void setAdded(boolean added) {
//        this.added = added;
//    }
//
//    public EmployeeSelectorView getEmployeeSelectorView() {
//        return employeeSelectorView;
//    }
//
//    public void setEmployeeSelectorView(EmployeeSelectorView employeeSelectorView) {
//        this.employeeSelectorView = employeeSelectorView;
//    }
    @Override
    public void find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
