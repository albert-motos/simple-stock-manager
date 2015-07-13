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
            name = "SexTypeTranslation.findAllForSelector",
            query = "SELECT s FROM SexTypeTranslation s WHERE s.language.code = :language"
    ),
    @NamedQuery(
            name = "SexTypeTranslation.findEnableForSelector",
            query = "SELECT s FROM SexTypeTranslation s WHERE s.language.code = :language AND s.reference.enable = TRUE"
    )
})
public class SexTypeTranslationQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
