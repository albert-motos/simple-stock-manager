package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.controller.ProductJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for Product object
 *
 * @author foxtrot
 */
 public class ProductHelper extends CommonHelper {

    public ProductHelper() {
        super(BusinessConstant.QUERY.PRODUCT);
    }

    public ProductJpaController getJpaController() {
        return new ProductJpaController(entityManagerFactory);
    }

    public Query getFindByRelationQuery(ProductType productType, Brand brand, Provider provider) {
        Query query = entityManager.createNamedQuery("Product.findByRelation");
        query.setParameter("productType", productType);
        query.setParameter("brand", brand);
        query.setParameter("provider", provider);

        return query;
    }

    public Query getFindForBrowserQuery(long brandID, String description, String name, long productTypeID, long providerID, long status, Date createdDateFrom,
            Date createdDateTo, Date lastModifiedDateFrom, Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {

        String query = "SELECT p FROM Product p where 1 = 1"
                + (brandID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND p.brand.id = " + brandID)
                + (description.isEmpty() ? "" : " AND p.description LIKE '%" + description + "%'")
                + (name.isEmpty() ? "" : " AND p.name LIKE '%" + name + "%'")
                + (productTypeID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND p.productType.id = " + productTypeID)
                + (providerID == BusinessConstant.IDENTIFIER.INVALID ? "" : " AND p.provider.id = " + providerID)
                + (status == WebConstant.STATUS.HIDDEN ? " AND p.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND p.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);

        return entityManager.createQuery(query);
    }
    
    public Query getFindAllByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Product.findAllByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByBrowser(String browser) {
        Query query = entityManager.createNamedQuery("Product.findEnableByBrowser");
        query.setParameter("browser", "%" + browser + "%");
        
        return query;
    }
    
    public Query getFindEnableByStore(String browser, String store) {
        Query query = entityManager.createNamedQuery("Product.findEnableByStore");
        query.setParameter("browser", "%" + browser + "%");
        query.setParameter("store", store);
        
        return query;
    }
}
