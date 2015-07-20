package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Store object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Store.findAllByBrowser",
            query = "SELECT s FROM Store s WHERE s.name LIKE :browser ORDER BY s.name"
    ),
    @NamedQuery(
            name = "Store.findEnableByBrowser",
            query = "SELECT s FROM Store s WHERE s.name LIKE :browser AND s.enable = TRUE ORDER BY s.name"
    )
})
public class StoreQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
