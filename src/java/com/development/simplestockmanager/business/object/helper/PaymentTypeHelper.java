package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.persistence.controller.PaymentTypeJpaController;
import javax.persistence.Query;

/**
 * Helper class for PaymentType object
 *
 * @author foxtrot
 */
public class PaymentTypeHelper extends CommonHelper implements BaseTypeHelper {

    public PaymentTypeJpaController getJpaController() {
        return new PaymentTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("PaymnetType.findByType");
        query.setParameter("type", type);

        return query;
    }

    @Override
    public Query getFindAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Query getFindEnable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
