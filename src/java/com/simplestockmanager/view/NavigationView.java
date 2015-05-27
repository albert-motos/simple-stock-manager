/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class NavigationView {

    private ExternalContext externalContext;

    public NavigationView() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
    }

    public void goToIndex() throws IOException {
        externalContext.redirect("/SimpleStockManager/index.xhtml");
    }

}
