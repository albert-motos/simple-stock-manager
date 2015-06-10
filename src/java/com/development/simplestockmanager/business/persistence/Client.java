/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "Client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByFirstName", query = "SELECT c FROM Client c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Client.findByLastName", query = "SELECT c FROM Client c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Client.findByBornDate", query = "SELECT c FROM Client c WHERE c.bornDate = :bornDate"),
    @NamedQuery(name = "Client.findBySexTypeID", query = "SELECT c FROM Client c WHERE c.sexTypeID = :sexTypeID"),
    @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = "Client.findByIsEnable", query = "SELECT c FROM Client c WHERE c.isEnable = :isEnable"),
    @NamedQuery(name = "Client.findByCreatedDate", query = "SELECT c FROM Client c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Client.findByLastModifiedDate", query = "SELECT c FROM Client c WHERE c.lastModifiedDate = :lastModifiedDate")})
public class Client implements Serializable {

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

    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public Client(String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, boolean isEnable, Date createdDate, Date lastModifiedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.sexTypeID = sexTypeID;
        this.phone = phone;
        this.email = email;
        this.isEnable = isEnable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Client(Long id, String firstName, String lastName, Date bornDate, long sexTypeID, String phone, String email, boolean isEnable, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.sexTypeID = sexTypeID;
        this.phone = phone;
        this.email = email;
        this.isEnable = isEnable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.bornDate, other.bornDate)) {
            return false;
        }
        if (this.sexTypeID != other.sexTypeID) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.isEnable != other.isEnable) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.Client[ id=" + id + " ]";
    }

}
