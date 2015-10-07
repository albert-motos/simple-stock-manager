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
            name = "Price.findEnableByStore",
            query = "SELECT DISTINCT P FROM Price p INNER JOIN Stock s ON p.stock.id = s.id AND s.store.id = (SELECT st.id FROM Store st WHERE st.name = :store)"
                    + " AND s.product.id = (SELECT pr.id FROM Product pr WHERE pr.name = :product) WHERE p.enable = TRUE"
    )
})
public class PriceQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
