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
@Table(name = "PRODUCT_TYPE_TRANSLATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductTypeTranslation.findAll", query = "SELECT p FROM ProductTypeTranslation p"),
    @NamedQuery(name = "ProductTypeTranslation.findById", query = "SELECT p FROM ProductTypeTranslation p WHERE p.id = :id"),
    @NamedQuery(name = "ProductTypeTranslation.findByTranslation", query = "SELECT p FROM ProductTypeTranslation p WHERE p.translation = :translation")})
public class ProductTypeTranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TRANSLATION")
    private String translation;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "REFERENCE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductType reference;

    public ProductTypeTranslation() {
    }

    public ProductTypeTranslation(Long id) {
        this.id = id;
    }

    public ProductTypeTranslation(ProductTypeTranslation productTypeTranslation) {
        this.id = productTypeTranslation.id;
        this.language = productTypeTranslation.language;
        this.reference = productTypeTranslation.reference;
        this.translation = productTypeTranslation.translation;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ProductType getReference() {
        return reference;
    }

    public void setReference(ProductType reference) {
        this.reference = reference;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.translation);
        hash = 29 * hash + Objects.hashCode(this.language);
        hash = 29 * hash + Objects.hashCode(this.reference);
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
        final ProductTypeTranslation other = (ProductTypeTranslation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.translation, other.translation)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        return Objects.equals(this.reference, other.reference);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.ProductTypeTranslation[ id=" + id + " ]";
    }
    
}
