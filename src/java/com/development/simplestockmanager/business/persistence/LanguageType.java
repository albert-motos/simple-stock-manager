/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "LANGUAGE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LanguageType.findAll", query = "SELECT l FROM LanguageType l"),
    @NamedQuery(name = "LanguageType.findById", query = "SELECT l FROM LanguageType l WHERE l.id = :id"),
    @NamedQuery(name = "LanguageType.findByCode", query = "SELECT l FROM LanguageType l WHERE l.code = :code"),
    @NamedQuery(name = "LanguageType.findByLanguage", query = "SELECT l FROM LanguageType l WHERE l.language = :language")})
public class LanguageType implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "languageType")
    private List<PriceType> priceTypeList;
    @OneToMany(mappedBy = "referencedType")
    private List<LanguageType> languageTypeList;
    @JoinColumn(name = "REFERENCED_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private LanguageType referencedType;
    @OneToMany(mappedBy = "languageType")
    private List<SexType> sexTypeList;
    @OneToMany(mappedBy = "languageType")
    private List<PaymentType> paymentTypeList;
    @OneToMany(mappedBy = "languageType")
    private List<EmployeeType> employeeTypeList;
    @OneToMany(mappedBy = "languageType")
    private List<ProductType> productTypeList;

    public LanguageType() {
    }

    public LanguageType(Long id) {
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
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<PriceType> getPriceTypeList() {
        return priceTypeList;
    }

    public void setPriceTypeList(List<PriceType> priceTypeList) {
        this.priceTypeList = priceTypeList;
    }

    @XmlTransient
    public List<LanguageType> getLanguageTypeList() {
        return languageTypeList;
    }

    public void setLanguageTypeList(List<LanguageType> languageTypeList) {
        this.languageTypeList = languageTypeList;
    }

    public LanguageType getReferencedType() {
        return referencedType;
    }

    public void setReferencedType(LanguageType referencedType) {
        this.referencedType = referencedType;
    }

    @XmlTransient
    public List<SexType> getSexTypeList() {
        return sexTypeList;
    }

    public void setSexTypeList(List<SexType> sexTypeList) {
        this.sexTypeList = sexTypeList;
    }

    @XmlTransient
    public List<PaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    @XmlTransient
    public List<EmployeeType> getEmployeeTypeList() {
        return employeeTypeList;
    }

    public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
        this.employeeTypeList = employeeTypeList;
    }

    @XmlTransient
    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.code);
        hash = 73 * hash + Objects.hashCode(this.language);
        hash = 73 * hash + Objects.hashCode(this.referencedType);
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
        final LanguageType other = (LanguageType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        return Objects.equals(this.referencedType, other.referencedType);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.LanguageType[ id=" + id + " ]";
    }
    
}
