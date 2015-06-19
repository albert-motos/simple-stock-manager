/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.old;

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
@Table(name = "Record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findById", query = "SELECT r FROM Record r WHERE r.id = :id"),
    @NamedQuery(name = "Record.findByEmployeeID", query = "SELECT r FROM Record r WHERE r.employeeID = :employeeID"),
    @NamedQuery(name = "Record.findByStockID", query = "SELECT r FROM Record r WHERE r.stockID = :stockID"),
    @NamedQuery(name = "Record.findByAmount", query = "SELECT r FROM Record r WHERE r.amount = :amount"),
    @NamedQuery(name = "Record.findByNote", query = "SELECT r FROM Record r WHERE r.note = :note"),
    @NamedQuery(name = "Record.findByCreatedDate", query = "SELECT r FROM Record r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "Record.findByLastModifiedDate", query = "SELECT r FROM Record r WHERE r.lastModifiedDate = :lastModifiedDate")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EmployeeID")
    private long employeeID;
    @Basic(optional = false)
    @Column(name = "StockID")
    private long stockID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "Note")
    private String note;
    @Basic(optional = false)
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LastModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public Record() {
    }

    public Record(Long id) {
        this.id = id;
    }

    public Record(long employeeID, long stockID, BigDecimal amount, String note, Date createdDate, Date lastModifiedDate) {
        this.employeeID = employeeID;
        this.stockID = stockID;
        this.amount = amount;
        this.note = note;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public Record(Long id, long employeeID, long stockID, BigDecimal amount, String note, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.employeeID = employeeID;
        this.stockID = stockID;
        this.amount = amount;
        this.note = note;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public long getStockID() {
        return stockID;
    }

    public void setStockID(long stockID) {
        this.stockID = stockID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.Record[ id=" + id + " ]";
    }
    
}
