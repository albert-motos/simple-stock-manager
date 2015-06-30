package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for LanguageType object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "LanguageType.findAllForSelector", query = "SELECT l FROM LanguageType l WHERE l.code = :language AND l.language IS NOT NULL")})
public class LanguageTypeQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
