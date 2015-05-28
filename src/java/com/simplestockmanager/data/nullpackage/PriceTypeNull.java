/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.nullpackage;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.persistence.PriceType;

/**
 *
 * @author foxtrot
 */
public class PriceTypeNull extends PriceType {

    public PriceTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
    
}
