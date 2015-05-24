/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.StoreNull;
import com.simplestockmanager.helper.StoreHelper;
import com.simplestockmanager.persistence.Store;
import com.simplestockmanager.persistence.controller.StoreJpaController;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class StoreGeneralController {
    
    public static long create(String name, String street, String city, String state, String country, String phone, long managerID, boolean isEnable, Date createdDate,
            Date lastModifiedDate) {
        
        Store store = new Store(name, street, city, state, country, phone, managerID, isEnable, createdDate, lastModifiedDate);
        
        try {
            StoreJpaController storeJpaController = StoreHelper.getJpaController();
            storeJpaController.create(store);
        } catch (Exception e) {
            store = new StoreNull();
        }
        
        return store.getId();
    }
    
    public static Store read(long id) {
        
        Store store;
        
        try {
            Query query = StoreHelper.getFindByIdQuery(id);
            store = (Store) query.getSingleResult();
        } catch (Exception e) {
            store = new StoreNull();
        }
        
        return store;
    }
    
    public static long update(long id, String name, String street, String city, String state, String country, String phone, long managerID, boolean isEnable,
            Date createdDate, Date lastModifiedDate) {
        
        long status = UpdateConstant.FAILURE;
        
        if (read(id).getId() != IdentifierConstant.INVALID) {
            Store store = new Store(id, name, street, city, state, country, phone, managerID, isEnable, createdDate, lastModifiedDate);
            
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.edit(store);
                status = UpdateConstant.SUCCESS;
            } catch (Exception e) {
            }
        }
        
        return status;
    }
    
    public static long delete(long id) {
        
        long status = DeleteConstant.FAILURE;
        
        if (read(id).getId() != IdentifierConstant.INVALID) {
            try {
                StoreJpaController storeJpaController = StoreHelper.getJpaController();
                storeJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {
            }
        }
        
        return status;
    }
}
