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
@Table(name = "EMPLOYEE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeType.findAll", query = "SELECT e FROM EmployeeType e"),
    @NamedQuery(name = "EmployeeType.findById", query = "SELECT e FROM EmployeeType e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeeType.findByType", query = "SELECT e FROM EmployeeType e WHERE e.type = :type"),
    @NamedQuery(name = "EmployeeType.findByEnable", query = "SELECT e FROM EmployeeType e WHERE e.enable = :enable"),
    @NamedQuery(name = "EmployeeType.findByCreatedDate", query = "SELECT e FROM EmployeeType e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EmployeeType.findByLastModifiedDate", query = "SELECT e FROM EmployeeType e WHERE e.lastModifiedDate = :lastModifiedDate")})
public class EmployeeType implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeType")
    private List<Employee> employeeList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @OneToMany(mappedBy = "referencedType")
    private List<EmployeeType> employeeTypeList;
    @JoinColumn(name = "REFERENCED_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private EmployeeType referencedType;
    @JoinColumn(name = "LANGUAGE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private LanguageType languageType;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;

    public EmployeeType() {
    }

    public EmployeeType(Long id) {
        this.id = id;
    }

    public EmployeeType(Long id, String type, boolean enable, Date createdDate, Date lastModifiedDate) {
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
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    @XmlTransient
    public List<EmployeeType> getEmployeeTypeList() {
        return employeeTypeList;
    }

    public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
        this.employeeTypeList = employeeTypeList;
    }

    public EmployeeType getReferencedType() {
        return referencedType;
    }

    public void setReferencedType(EmployeeType referencedType) {
        this.referencedType = referencedType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
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
        if (!(object instanceof EmployeeType)) {
            return false;
        }
        EmployeeType other = (EmployeeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.EmployeeType[ id=" + id + " ]";
    }
    
}
