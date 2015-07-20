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
            name = "SexTypeTranslation.getFindByTranslationAndLanguage",
            query = "SELECT s FROM SexTypeTranslation s WHERE s.translation = :translation AND s.language.code = :language"
    )
})
public class SexTypeTranslationQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
