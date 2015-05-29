/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.common;

import com.simplestockmanager.common.Constant;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class UrlView {

    public String index() {
        return Constant.URL.INDEX;
    }
    
}
