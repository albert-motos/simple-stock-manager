/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.persistence.old.PriceType;

/**
 *
 * @author foxtrot
 */
public class PriceTypeNull extends PriceType {

    public PriceTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
    
}