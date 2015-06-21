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
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findByBornDate", query = "SELECT e FROM Employee e WHERE e.bornDate = :bornDate"),
    @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.username = :username"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByIsOnline", query = "SELECT e FROM Employee e WHERE e.isOnline = :isOnline"),
    @NamedQuery(name = "Employee.findByLastOnlineDate", query = "SELECT e FROM Employee e WHERE e.lastOnlineDate = :lastOnlineDate"),
    @NamedQuery(name = "Employee.findBySessionId", query = "SELECT e FROM Employee e WHERE e.sessionId = :sessionId"),
    @NamedQuery(name = "Employee.findByEnable", query = "SELECT e FROM Employee e WHERE e.enable = :enable"),
    @NamedQuery(name = "Employee.findByCreatedDate", query = "SELECT e FROM Employee e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "Employee.findByCreatedUser", query = "SELECT e FROM Employee e WHERE e.createdUser = :createdUser"),
    @NamedQuery(name = "Employee.findByLastModifiedDate", query = "SELECT e FROM Employee e WHERE e.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Employee.findByLastModifiedUser", query = "SELECT e FROM Employee e WHERE e.lastModifiedUser = :lastModifiedUser")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "BORN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;
    @Basic(optional = false)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "IS_ONLINE")
    private boolean isOnline;
    @Basic(optional = false)
    @Column(name = "LAST_ONLINE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineDate;
    @Column(name = "SESSION_ID")
    private String sessionId;
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
    @JoinColumn(name = "EMPLOYEE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmployeeType employeeType;
    @JoinColumn(name = "SEX_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SexType sexType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Invoice> invoiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Store> storeList;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, String firstname, String lastname, Date bornDate, String phone, String email, String username, String password, boolean isOnline, Date lastOnlineDate, boolean enable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornDate = bornDate;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isOnline = isOnline;
        this.lastOnlineDate = lastOnlineDate;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setLastOnlineDate(Date lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    @XmlTransient
    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @XmlTransient
    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Employee[ id=" + id + " ]";
    }
    
}
