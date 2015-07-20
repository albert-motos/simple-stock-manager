package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for PriceTypeTranslation object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "PriceTypeTranslation.getFindByTranslationAndLanguage",
            query = "SELECT p FROM PriceTypeTranslation p WHERE p.translation = :translation AND p.language.code = :language"
    )
})
public class PriceTypeTranslationQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
