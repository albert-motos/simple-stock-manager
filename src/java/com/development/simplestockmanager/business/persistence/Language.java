/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "LANGUAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l"),
    @NamedQuery(name = "Language.findById", query = "SELECT l FROM Language l WHERE l.id = :id"),
    @NamedQuery(name = "Language.findByCode", query = "SELECT l FROM Language l WHERE l.code = :code"),
    @NamedQuery(name = "Language.findByLanguage", query = "SELECT l FROM Language l WHERE l.language = :language")})
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "LANGUAGE")
    private String language;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<EmployeeTypeTranslation> employeeTypeTranslationList;
    @OneToMany(mappedBy = "reference")
    private List<Language> languageList;
    @JoinColumn(name = "REFERENCE", referencedColumnName = "ID")
    @ManyToOne
    private Language reference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<Employee> employeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<SexTypeTranslation> sexTypeTranslationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<PriceTypeTranslation> priceTypeTranslationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<ProductTypeTranslation> productTypeTranslationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private List<PaymentTypeTranslation> paymentTypeTranslationList;

    public Language() {
    }

    public Language(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlTransient
    public List<EmployeeTypeTranslation> getEmployeeTypeTranslationList() {
        return employeeTypeTranslationList;
    }

    public void setEmployeeTypeTranslationList(List<EmployeeTypeTranslation> employeeTypeTranslationList) {
        this.employeeTypeTranslationList = employeeTypeTranslationList;
    }

    @XmlTransient
    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public Language getReference() {
        return reference;
    }

    public void setReference(Language reference) {
        this.reference = reference;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<SexTypeTranslation> getSexTypeTranslationList() {
        return sexTypeTranslationList;
    }

    public void setSexTypeTranslationList(List<SexTypeTranslation> sexTypeTranslationList) {
        this.sexTypeTranslationList = sexTypeTranslationList;
    }

    @XmlTransient
    public List<PriceTypeTranslation> getPriceTypeTranslationList() {
        return priceTypeTranslationList;
    }

    public void setPriceTypeTranslationList(List<PriceTypeTranslation> priceTypeTranslationList) {
        this.priceTypeTranslationList = priceTypeTranslationList;
    }

    @XmlTransient
    public List<ProductTypeTranslation> getProductTypeTranslationList() {
        return productTypeTranslationList;
    }

    public void setProductTypeTranslationList(List<ProductTypeTranslation> productTypeTranslationList) {
        this.productTypeTranslationList = productTypeTranslationList;
    }

    @XmlTransient
    public List<PaymentTypeTranslation> getPaymentTypeTranslationList() {
        return paymentTypeTranslationList;
    }

    public void setPaymentTypeTranslationList(List<PaymentTypeTranslation> paymentTypeTranslationList) {
        this.paymentTypeTranslationList = paymentTypeTranslationList;
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Language[ id=" + id + " ]";
    }
    
}
