/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findByClientID", query = "SELECT i FROM Invoice i WHERE i.clientID = :clientID"),
    @NamedQuery(name = "Invoice.findByEmployeeID", query = "SELECT i FROM Invoice i WHERE i.employeeID = :employeeID"),
    @NamedQuery(name = "Invoice.findByPaymentTypeID", query = "SELECT i FROM Invoice i WHERE i.paymentTypeID = :paymentTypeID"),
    @NamedQuery(name = "Invoice.findByCost", query = "SELECT i FROM Invoice i WHERE i.cost = :cost"),
    @NamedQuery(name = "Invoice.findByCreatedDate", query = "SELECT i FROM Invoice i WHERE i.createdDate = :createdDate"),
    @NamedQuery(name = "Invoice.findByLastModifiedDate", query = "SELECT i FROM Invoice i WHERE i.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Invoice.findByAnalitycsTimeID", query = "SELECT i FROM Invoice i WHERE i.analitycsTimeID = :analitycsTimeID")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ClientID")
    private long clientID;
    @Basic(optional = false)
    @Column(name = "EmployeeID")
    private long employeeID;
    @Basic(optional = false)
    @Column(name = "PaymentTypeID")
    private long paymentTypeID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LastModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Basic(optional = false)
    @Column(name = "AnalitycsTimeID")
    private long analitycsTimeID;

    public Invoice() {
    }

    public Invoice(Long id) {
        this.id = id;
    }

    public Invoice(long clientID, long employeeID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, long analitycsTimeID) {
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.paymentTypeID = paymentTypeID;
        this.cost = cost;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.analitycsTimeID = analitycsTimeID;
    }

    public Invoice(Long id, long clientID, long employeeID, long paymentTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, long analitycsTimeID) {
        this.id = id;
        this.clientID = clientID;
        this.employeeID = employeeID;
        this.paymentTypeID = paymentTypeID;
        this.cost = cost;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.analitycsTimeID = analitycsTimeID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public long getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(long paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    public long getAnalitycsTimeID() {
        return analitycsTimeID;
    }

    public void setAnalitycsTimeID(long analitycsTimeID) {
        this.analitycsTimeID = analitycsTimeID;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.Invoice[ id=" + id + " ]";
    }
    
}
