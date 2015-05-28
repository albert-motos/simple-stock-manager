/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.nullpackage;

import com.simplestockmanager.common.Constant;
import com.simplestockmanager.persistence.PaymentType;

/**
 *
 * @author foxtrot
 */
public class PaymentTypeNull extends PaymentType {

    public PaymentTypeNull() {
        super(Constant.IDENTIFIER.INVALID);
    }
    
}
