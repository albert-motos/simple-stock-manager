/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import java.io.IOException;
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

    public void redirect(String url) throws IOException {
        currentInstance.getExternalContext().redirect(url);
    }
    
}
