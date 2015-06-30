/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "PAYMENT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentType.findAll", query = "SELECT p FROM PaymentType p"),
    @NamedQuery(name = "PaymentType.findById", query = "SELECT p FROM PaymentType p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentType.findByType", query = "SELECT p FROM PaymentType p WHERE p.type = :type"),
    @NamedQuery(name = "PaymentType.findByEnable", query = "SELECT p FROM PaymentType p WHERE p.enable = :enable"),
    @NamedQuery(name = "PaymentType.findByCreatedDate", query = "SELECT p FROM PaymentType p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PaymentType.findByCreatedUser", query = "SELECT p FROM PaymentType p WHERE p.createdUser = :createdUser"),
    @NamedQuery(name = "PaymentType.findByLastModifiedDate", query = "SELECT p FROM PaymentType p WHERE p.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "PaymentType.findByLastModifiedUser", query = "SELECT p FROM PaymentType p WHERE p.lastModifiedUser = :lastModifiedUser")})
public class PaymentType implements Serializable {
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
    @Column(name = "CREATED_USER")
    private String createdUser;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_USER")
    private String lastModifiedUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentType")
    private List<Invoice> invoiceList;
    @JoinColumn(name = "LANGUAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private LanguageType languageType;
    @OneToMany(mappedBy = "referencedType")
    private List<PaymentType> paymentTypeList;
    @JoinColumn(name = "REFERENCED_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private PaymentType referencedType;

    public PaymentType() {
    }

    public PaymentType(Long id) {
        this.id = id;
    }

    public PaymentType(Long id, String type, boolean enable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.type = type;
        this.enable = enable;
        this.createdDate = createdDate;
        this.createdUser = createdUser;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedUser = lastModifiedUser;
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    @XmlTransient
    public List<PaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    public PaymentType getReferencedType() {
        return referencedType;
    }

    public void setReferencedType(PaymentType referencedType) {
        this.referencedType = referencedType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentType)) {
            return false;
        }
        PaymentType other = (PaymentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.PaymentType[ id=" + id + " ]";
    }
    
}
