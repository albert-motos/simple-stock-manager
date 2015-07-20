package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "EMPLOYEE_TYPE_TRANSLATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeTypeTranslation.findAll", query = "SELECT e FROM EmployeeTypeTranslation e"),
    @NamedQuery(name = "EmployeeTypeTranslation.findById", query = "SELECT e FROM EmployeeTypeTranslation e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeeTypeTranslation.findByTranslation", query = "SELECT e FROM EmployeeTypeTranslation e WHERE e.translation = :translation")})
public class EmployeeTypeTranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TRANSLATION")
    private String translation;
    @JoinColumn(name = "REFERENCE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmployeeType reference;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Language language;

    public EmployeeTypeTranslation() {
    }

    public EmployeeTypeTranslation(Long id) {
        this.id = id;
    }

    public EmployeeTypeTranslation(EmployeeTypeTranslation employeeTypeTranslation) {
        this.id = employeeTypeTranslation.id;
        this.language = employeeTypeTranslation.language;
        this.reference = employeeTypeTranslation.reference;
        this.translation = employeeTypeTranslation.translation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public EmployeeType getReference() {
        return reference;
    }

    public void setReference(EmployeeType reference) {
        this.reference = reference;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.translation);
        hash = 79 * hash + Objects.hashCode(this.reference);
        hash = 79 * hash + Objects.hashCode(this.language);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeTypeTranslation other = (EmployeeTypeTranslation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.translation, other.translation)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return Objects.equals(this.language, other.language);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation[ id=" + id + " ]";
    }
    
}
