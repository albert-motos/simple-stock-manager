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
@Table(name = "SEX_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SexType.findAll", query = "SELECT s FROM SexType s"),
    @NamedQuery(name = "SexType.findById", query = "SELECT s FROM SexType s WHERE s.id = :id"),
    @NamedQuery(name = "SexType.findByType", query = "SELECT s FROM SexType s WHERE s.type = :type"),
    @NamedQuery(name = "SexType.findByEnable", query = "SELECT s FROM SexType s WHERE s.enable = :enable"),
    @NamedQuery(name = "SexType.findByCreatedDate", query = "SELECT s FROM SexType s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SexType.findByCreatedUser", query = "SELECT s FROM SexType s WHERE s.createdUser = :createdUser"),
    @NamedQuery(name = "SexType.findByLastModifiedDate", query = "SELECT s FROM SexType s WHERE s.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "SexType.findByLastModifiedUser", query = "SELECT s FROM SexType s WHERE s.lastModifiedUser = :lastModifiedUser")})
public class SexType implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexType")
    private List<Employee> employeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexType")
    private List<Client> clientList;
    @JoinColumn(name = "LANGUAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private LanguageType languageType;
    @OneToMany(mappedBy = "referencedType")
    private List<SexType> sexTypeList;
    @JoinColumn(name = "REFERENCED_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private SexType referencedType;

    public SexType() {
    }

    public SexType(Long id) {
        this.id = id;
    }

    public SexType(Long id, String type, boolean enable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
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
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    @XmlTransient
    public List<SexType> getSexTypeList() {
        return sexTypeList;
    }

    public void setSexTypeList(List<SexType> sexTypeList) {
        this.sexTypeList = sexTypeList;
    }

    public SexType getReferencedType() {
        return referencedType;
    }

    public void setReferencedType(SexType referencedType) {
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
        if (!(object instanceof SexType)) {
            return false;
        }
        SexType other = (SexType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.SexType[ id=" + id + " ]";
    }
    
}
