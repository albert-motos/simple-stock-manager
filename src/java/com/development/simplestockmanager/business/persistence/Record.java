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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findById", query = "SELECT r FROM Record r WHERE r.id = :id"),
    @NamedQuery(name = "Record.findByAmount", query = "SELECT r FROM Record r WHERE r.amount = :amount"),
    @NamedQuery(name = "Record.findByNote", query = "SELECT r FROM Record r WHERE r.note = :note"),
    @NamedQuery(name = "Record.findByIsEnable", query = "SELECT r FROM Record r WHERE r.isEnable = :isEnable"),
    @NamedQuery(name = "Record.findByCreatedDate", query = "SELECT r FROM Record r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "Record.findByCreatedUser", query = "SELECT r FROM Record r WHERE r.createdUser = :createdUser"),
    @NamedQuery(name = "Record.findByLastModifiedDate", query = "SELECT r FROM Record r WHERE r.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Record.findByLastModifiedUser", query = "SELECT r FROM Record r WHERE r.lastModifiedUser = :lastModifiedUser")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "NOTE")
    private String note;
    @Basic(optional = false)
    @Column(name = "IS_ENABLE")
    private boolean isEnable;
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
    @JoinColumn(name = "ANALITYCS_TIME", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private AnalyticsTime analitycsTime;
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee employee;
    @JoinColumn(name = "STOCK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Stock stock;

    public Record() {
    }

    public Record(Long id) {
        this.id = id;
    }

    public Record(Long id, BigDecimal amount, boolean isEnable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.amount = amount;
        this.isEnable = isEnable;
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

    public AnalyticsTime getAnalitycsTime() {
        return analitycsTime;
    }

    public void setAnalitycsTime(AnalyticsTime analitycsTime) {
        this.analitycsTime = analitycsTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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
        return "DB.Record[ id=" + id + " ]";
    }
    
}
