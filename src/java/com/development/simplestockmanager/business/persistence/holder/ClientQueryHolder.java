package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Client object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Client.findAllByBrowser",
            query = "SELECT c FROM Client c WHERE c.firstname LIKE :browser OR c.lastname LIKE :browser ORDER BY c.lastname"
    ),
    @NamedQuery(
            name = "Client.findEnableByBrowser",
            query = "SELECT c FROM Client c WHERE c.firstname LIKE :browser OR c.lastname LIKE :browser AND c.enable = TRUE ORDER BY c.lastname"
    )
})
public class ClientQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
