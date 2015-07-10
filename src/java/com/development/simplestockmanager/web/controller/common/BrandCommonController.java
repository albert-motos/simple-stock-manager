/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.web.controller.common;

import com.development.simplestockmanager.business.object.controller.general.BrandGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.validator.BrandValidator;

/**
 *
 * @author albert.motos
 */
public class BrandCommonController extends BaseCommonController {

    protected final BrandValidator validator;
    protected final BrandGeneralController generalController;
    protected final BrandSpecificController specificController;

    protected final Brand brand;

    public BrandCommonController() {
        validator = new BrandValidator(WebConstant.VALIDATOR.MODE.CREATE, internationalizationController);
        generalController = new BrandGeneralController();
        specificController = new BrandSpecificController();

        brand = new Brand();
    }
    
    
}
