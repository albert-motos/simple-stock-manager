/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@SessionScoped
public class auth implements Serializable {

    public auth() {
        
        date = new Date();
        System.out.println("#INIT " + date + " -> " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));
    }

    
    public Date date;
    
    public String getHello() {
        return "HeLo";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

}
