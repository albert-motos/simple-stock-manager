
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
            name = "EmployeeTypeTranslation.findAllForSelector",
            query = "SELECT e FROM EmployeeTypeTranslation e WHERE e.language.code = :language"
    ),
    @NamedQuery(
            name = "EmployeeTypeTranslation.findEnableForSelector",
            query = "SELECT e FROM EmployeeTypeTranslation e WHERE e.language.code = :language AND e.reference.enable = TRUE"
    )
})
public class EmployeeTypeTranslationQueryHolder implements Serializable {

    @Id
    private Integer id;
}
