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
@Table(name = "Price")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
    @NamedQuery(name = "Price.findById", query = "SELECT p FROM Price p WHERE p.id = :id"),
    @NamedQuery(name = "Price.findByStockID", query = "SELECT p FROM Price p WHERE p.stockID = :stockID"),
    @NamedQuery(name = "Price.findByTitle", query = "SELECT p FROM Price p WHERE p.title = :title"),
    @NamedQuery(name = "Price.findByPriceTypeID", query = "SELECT p FROM Price p WHERE p.priceTypeID = :priceTypeID"),
    @NamedQuery(name = "Price.findByCost", query = "SELECT p FROM Price p WHERE p.cost = :cost"),
    @NamedQuery(name = "Price.findByCreatedDate", query = "SELECT p FROM Price p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Price.findByLastModifiedDate", query = "SELECT p FROM Price p WHERE p.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Price.findByInitialAmount", query = "SELECT p FROM Price p WHERE p.initialAmount = :initialAmount"),
    @NamedQuery(name = "Price.findByActualAmount", query = "SELECT p FROM Price p WHERE p.actualAmount = :actualAmount"),
    @NamedQuery(name = "Price.findByEndDate", query = "SELECT p FROM Price p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Price.findByIsEnable", query = "SELECT p FROM Price p WHERE p.isEnable = :isEnable")})
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "StockID")
    private long stockID;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @Column(name = "PriceTypeID")
    private long priceTypeID;
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
    @Column(name = "InitialAmount")
    private BigDecimal initialAmount;
    @Column(name = "ActualAmount")
    private BigDecimal actualAmount;
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "isEnable")
    private boolean isEnable;

    public Price() {
    }

    public Price(Long id) {
        this.id = id;
    }

    public Price(long stockID, String title, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, BigDecimal initialAmount, BigDecimal actualAmount, Date endDate, boolean isEnable) {
        this.stockID = stockID;
        this.title = title;
        this.priceTypeID = priceTypeID;
        this.cost = cost;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.initialAmount = initialAmount;
        this.actualAmount = actualAmount;
        this.endDate = endDate;
        this.isEnable = isEnable;
    }

    public Price(Long id, long stockID, String title, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, boolean isEnable) {
        this.id = id;
        this.stockID = stockID;
        this.title = title;
        this.priceTypeID = priceTypeID;
        this.cost = cost;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isEnable = isEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStockID() {
        return stockID;
    }

    public void setStockID(long stockID) {
        this.stockID = stockID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPriceTypeID() {
        return priceTypeID;
    }

    public void setPriceTypeID(long priceTypeID) {
        this.priceTypeID = priceTypeID;
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

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
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
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.Price[ id=" + id + " ]";
    }
    
}
