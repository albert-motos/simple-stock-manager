package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for SexType object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SexType.findAllForSelector", query = "SELECT s FROM SexType s WHERE s.languageType.code = :language AND s.enable = TRUE"),
    @NamedQuery(name = "SexType.getFindByRefencedType", query = "SELECT s FROM SexType s WHERE s.referencedType IS NULL AND s.type = :type"),
    @NamedQuery(name = "SexType.getFindByRefencedTypeAndLanguage", query = "SELECT s FROM SexType s WHERE s.referencedType.type = :type AND s.languageType.code = :language")})
public class SexTypeQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
