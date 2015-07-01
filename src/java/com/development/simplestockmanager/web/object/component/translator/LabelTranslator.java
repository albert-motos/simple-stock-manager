/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.object.component.translator;

import com.development.simplestockmanager.common.Constant;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author foxtrot
 */
@ManagedBean
@ApplicationScoped
public class LabelTranslator {

    public LabelTranslator() {
        System.out.println("#START");
    }
    
    public String getASD(){
        return "asd";
    }
    
//    private final String firstname;
//    private final String lastname;
//    private final String sexType;
//    private final String bornDate;
//    private final String phoneNumber;
//    private final String email;

//    public LabelTranslator() {
////        firstname = controller.getWord(Constant.LANGUAGE.LABEL.FIRSTNAME);
////        lastname = controller.getWord(Constant.LANGUAGE.LABEL.LASTNAME);
////        sexType = controller.getWord(Constant.LANGUAGE.LABEL.SEXTYPE);
////        bornDate = controller.getWord(Constant.LANGUAGE.LABEL.BORNDATE);
////        phoneNumber = controller.getWord(Constant.LANGUAGE.LABEL.PHONENUMBER);
////        email = controller.getWord(Constant.LANGUAGE.LABEL.EMAIL);
//    }
//    
    
    
}
