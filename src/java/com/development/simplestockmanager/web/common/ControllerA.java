/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.common;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "manageAccountController")
@RequestScoped
public class ControllerA implements Serializable{

    @ManagedProperty("#{auth}")
    private auth a;

    public auth getA() {
        return a;
    }

    public ControllerA() {
        System.out.println("#INIT-A " + new Date());
    }
    
    public void add() {
        System.out.println("#ADD " + a.getDate());
    }

}