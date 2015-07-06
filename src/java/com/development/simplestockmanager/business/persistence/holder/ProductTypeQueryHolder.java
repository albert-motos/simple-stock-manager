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
    @NamedQuery(name = "ProductType.findAllForSelector", query = "SELECT p FROM ProductType p WHERE p.languageType.code = :language AND p.enable = TRUE")})
public class ProductTypeQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
