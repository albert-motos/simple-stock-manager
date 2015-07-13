package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for SexTypeTranslation object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "ProductTypeTranslation.findAllForSelector",
            query = "SELECT p FROM ProductTypeTranslation p WHERE p.language.code = :language"
    ),
    @NamedQuery(
            name = "ProductTypeTranslation.findEnableForSelector",
            query = "SELECT p FROM ProductTypeTranslation p WHERE p.language.code = :language AND p.reference.enable = TRUE"
    )
})
public class ProductTypeTranslationQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
