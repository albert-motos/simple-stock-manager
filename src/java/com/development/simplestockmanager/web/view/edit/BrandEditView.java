/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.view.edit;

import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.common.service.general.NavigationService;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@ViewScoped
public class BrandEditView extends BaseEditView {
    
    private Brand brand;

    public BrandEditView() {
        try {
            brand = (Brand) receiveObjectFromSession(WebConstant.SESSION.BRAND);
        } catch (Exception e) {
            back();
        }
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.BRAND);
    }
    
}
