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
@Table(name = "Store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s"),
    @NamedQuery(name = "Store.findById", query = "SELECT s FROM Store s WHERE s.id = :id"),
    @NamedQuery(name = "Store.findByName", query = "SELECT s FROM Store s WHERE s.name = :name"),
    @NamedQuery(name = "Store.findByStreet", query = "SELECT s FROM Store s WHERE s.street = :street"),
    @NamedQuery(name = "Store.findByCity", query = "SELECT s FROM Store s WHERE s.city = :city"),
    @NamedQuery(name = "Store.findByState", query = "SELECT s FROM Store s WHERE s.state = :state"),
    @NamedQuery(name = "Store.findByCountry", query = "SELECT s FROM Store s WHERE s.country = :country"),
    @NamedQuery(name = "Store.findByPhone", query = "SELECT s FROM Store s WHERE s.phone = :phone"),
    @NamedQuery(name = "Store.findByManagerID", query = "SELECT s FROM Store s WHERE s.managerID = :managerID"),
    @NamedQuery(name = "Store.findByIsEnable", query = "SELECT s FROM Store s WHERE s.isEnable = :isEnable"),
    @NamedQuery(name = "Store.findByCreatedDate", query = "SELECT s FROM Store s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "Store.findByLastModifiedDate", query = "SELECT s FROM Store s WHERE s.lastModifiedDate = :lastModifiedDate")})
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Street")
    private String street;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @Column(name = "Country")
    private String country;
    @Basic(optional = false)
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "ManagerID")
    private long managerID;
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

    public Store() {
    }

    public Store(Long id) {
        this.id = id;
    }

    public Store(String name, String street, String city, String state, String country, String phone, long managerID, boolean isEnable, Date createdDate, Date lastModifiedDate) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.managerID = managerID;
        this.isEnable = isEnable;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Store(Long id, String name, String street, String city, String state, String country, String phone, long managerID, boolean isEnable, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.managerID = managerID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getManagerID() {
        return managerID;
    }

    public void setManagerID(long managerID) {
        this.managerID = managerID;
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
        final Store other = (Store) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (this.managerID != other.managerID) {
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
        return "com.simplestockmanager.persistence.Store[ id=" + id + " ]";
    }
    
}
