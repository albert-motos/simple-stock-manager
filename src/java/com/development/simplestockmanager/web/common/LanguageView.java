/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import com.development.simplestockmanager.business.common.Constant;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@ManagedBean
public class LanguageView {

    public String backButtonValue(boolean finish) {
        return (finish ? Constant.VIEW.BUTTON.AFTER : Constant.VIEW.BUTTON.BEFORE);
    }
}