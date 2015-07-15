package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Product object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Product.findByRelation",
            query = "SELECT p FROM Product p WHERE p.productType = :productType AND p.brand = :brand AND p.provider = :provider")
})
public class ProductQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
