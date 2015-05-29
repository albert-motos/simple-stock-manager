/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "Employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByBornDate", query = "SELECT e FROM Employee e WHERE e.bornDate = :bornDate"),
    @NamedQuery(name = "Employee.findBySexTypeID", query = "SELECT e FROM Employee e WHERE e.sexTypeID = :sexTypeID"),
    @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByEmployeTypeID", query = "SELECT e FROM Employee e WHERE e.employeTypeID = :employeTypeID"),
    @NamedQuery(name = "Employee.findByIsEnable", query = "SELECT e FROM Employee e WHERE e.isEnable = :isEnable"),
    @NamedQuery(name = "Employee.findByCreatedDate", query = "SELECT e FROM Employee e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "Employee.findByLastModifiedDate", query = "SELECT e FROM Employee e WHERE e.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Employee.findByUserName", query = "SELECT e FROM Employee e WHERE e.userName = :userName"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByIsOnline", query = "SELECT e FROM Employee e WHERE e.isOnline = :isOnline"),
    @NamedQuery(name = "Employee.findByLastOnlineDate", query = "SELECT e FROM Employee e WHERE e.lastOnlineDate = :lastOnlineDate"),
    @NamedQuery(name = "Employee.findBySessionID", query = "SELECT e FROM Employee e WHERE e.sessionID = :sessionID")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "BornDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;
    @Basic(optional = false)
    @Column(name = "SexTypeID")
    private long sexTypeID;
    @Basic(optional = false)
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "EmployeTypeID")
    private long employeTypeID;
    @Basic(optional = false)
    @Column(name = "isEnable")
    private boolean isEnable;
    @Basic(optional = false)
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LastModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Basic(optional = false)
    @Column(name = "UserName")
    private String userName;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "isOnline")
    private boolean isOnline;
    @Basic(optional = false)
    @Column(name = "LastOnlineDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineDate;
    @Basic(optional = false)
    @Column(name = "SessionID")
    private String sessionID;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate, Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.sexTypeID = sexTypeID;
        this.phone = phone;
        this.email = email;
        this.employeTypeID = employeTypeID;
        this.isEnable = isEnable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userName = userName;
        this.password = password;
        this.isOnline = isOnline;
        this.lastOnlineDate = lastOnlineDate;
        this.sessionID = sessionID;
    }

    public Employee(Long id, String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, long employeTypeID, boolean isEnable, Date createdDate, Date lastModifiedDate, String userName, String password, boolean isOnline, Date lastOnlineDate, String sessionID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.sexTypeID = sexTypeID;
        this.phone = phone;
        this.email = email;
        this.employeTypeID = employeTypeID;
        this.isEnable = isEnable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userName = userName;
        this.password = password;
        this.isOnline = isOnline;
        this.lastOnlineDate = lastOnlineDate;
        this.sessionID = sessionID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public long getSexTypeID() {
        return sexTypeID;
    }

    public void setSexTypeID(long sexTypeID) {
        this.sexTypeID = sexTypeID;
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

    public long getEmployeTypeID() {
        return employeTypeID;
    }

    public void setEmployeTypeID(long employeTypeID) {
        this.employeTypeID = employeTypeID;
    }

    public boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
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
        return "com.simplestockmanager.persistence.Employee[ id=" + id + " ]";
    }
    
}
