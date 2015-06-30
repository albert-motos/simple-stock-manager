/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class NavigationView {

    private static FacesContext currentInstance;

    public NavigationView() {
        currentInstance = FacesContext.getCurrentInstance();
        
    }

    public void redirect(String url) {
        try {
            currentInstance.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(NavigationView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
