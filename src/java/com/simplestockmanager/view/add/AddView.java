/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.view.add;

/**
 *
 * @author foxtrot
 */
public interface AddView {
    /**
     * Main function of add view
     */
    public void add();
    
    /**
     * Function check if the fields are correct for create the object
     * @return true if fields are correct
     */
    public boolean validate();
}
