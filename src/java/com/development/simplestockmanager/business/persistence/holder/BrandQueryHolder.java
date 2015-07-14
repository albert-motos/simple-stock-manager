package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Brand object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Brand.getFindAllByBrowser",
            query = "SELECT b FROM Brand b WHERE b.name LIKE :browser ORDER BY b.name"
    ),
    @NamedQuery(
            name = "Brand.getFindEnableByBrowser",
            query = "SELECT b FROM Brand b WHERE b.name LIKE :browser AND b.enable = TRUE ORDER BY b.name"
    )
})
public class BrandQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
