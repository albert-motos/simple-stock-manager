package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for EmployeeTypeTranslation object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "EmployeeTypeTranslation.findByTranslationAndLanguage",
            query = "SELECT e FROM EmployeeTypeTranslation e WHERE e.translation = :translation AND e.language.code = :language"
    )
})
public class EmployeeTypeTranslationQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}
