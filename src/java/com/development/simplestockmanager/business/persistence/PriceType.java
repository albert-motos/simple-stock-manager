/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "PRICE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PriceType.findAll", query = "SELECT p FROM PriceType p"),
    @NamedQuery(name = "PriceType.findById", query = "SELECT p FROM PriceType p WHERE p.id = :id"),
    @NamedQuery(name = "PriceType.findByType", query = "SELECT p FROM PriceType p WHERE p.type = :type"),
    @NamedQuery(name = "PriceType.findByEnable", query = "SELECT p FROM PriceType p WHERE p.enable = :enable"),
    @NamedQuery(name = "PriceType.findByCreatedDate", query = "SELECT p FROM PriceType p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PriceType.findByLastModifiedDate", query = "SELECT p FROM PriceType p WHERE p.lastModifiedDate = :lastModifiedDate")})
public class PriceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private boolean enable;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceType")
    private List<Price> priceList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "LANGUAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private LanguageType languageType;
    @OneToMany(mappedBy = "referencedType")
    private List<PriceType> priceTypeList;
    @JoinColumn(name = "REFERENCED_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private PriceType referencedType;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;

    public PriceType() {
    }

    public PriceType(Long id) {
        this.id = id;
    }

    public PriceType(Long id, String type, boolean enable, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.type = type;
        this.enable = enable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @XmlTransient
    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    @XmlTransient
    public List<PriceType> getPriceTypeList() {
        return priceTypeList;
    }

    public void setPriceTypeList(List<PriceType> priceTypeList) {
        this.priceTypeList = priceTypeList;
    }

    public PriceType getReferencedType() {
        return referencedType;
    }

    public void setReferencedType(PriceType referencedType) {
        this.referencedType = referencedType;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + (this.enable ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.createdDate);
        hash = 29 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 29 * hash + Objects.hashCode(this.createdUser);
        hash = 29 * hash + Objects.hashCode(this.languageType);
        hash = 29 * hash + Objects.hashCode(this.referencedType);
        hash = 29 * hash + Objects.hashCode(this.lastModifiedUser);
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
        final PriceType other = (PriceType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.enable != other.enable) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        if (!Objects.equals(this.createdUser, other.createdUser)) {
            return false;
        }
        if (!Objects.equals(this.languageType, other.languageType)) {
            return false;
        }
        if (!Objects.equals(this.referencedType, other.referencedType)) {
            return false;
        }
        return Objects.equals(this.lastModifiedUser, other.lastModifiedUser);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.PriceType[ id=" + id + " ]";
    }
    
}
