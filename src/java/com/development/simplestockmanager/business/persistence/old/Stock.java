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
@Table(name = "Stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id"),
    @NamedQuery(name = "Stock.findByProductID", query = "SELECT s FROM Stock s WHERE s.productID = :productID"),
    @NamedQuery(name = "Stock.findByBrandID", query = "SELECT s FROM Stock s WHERE s.brandID = :brandID"),
    @NamedQuery(name = "Stock.findByStoreID", query = "SELECT s FROM Stock s WHERE s.storeID = :storeID"),
    @NamedQuery(name = "Stock.findByProviderID", query = "SELECT s FROM Stock s WHERE s.providerID = :providerID"),
    @NamedQuery(name = "Stock.findByActualAmount", query = "SELECT s FROM Stock s WHERE s.actualAmount = :actualAmount"),
    @NamedQuery(name = "Stock.findByTotalAmount", query = "SELECT s FROM Stock s WHERE s.totalAmount = :totalAmount"),
    @NamedQuery(name = "Stock.findByCreatedDate", query = "SELECT s FROM Stock s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "Stock.findByLastModifiedDate", query = "SELECT s FROM Stock s WHERE s.lastModifiedDate = :lastModifiedDate")})
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ProductID")
    private long productID;
    @Basic(optional = false)
    @Column(name = "BrandID")
    private long brandID;
    @Basic(optional = false)
    @Column(name = "StoreID")
    private long storeID;
    @Basic(optional = false)
    @Column(name = "ProviderID")
    private long providerID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "ActualAmount")
    private BigDecimal actualAmount;
    @Basic(optional = false)
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;
    @Basic(optional = false)
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LastModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public Stock() {
    }

    public Stock(Long id) {
        this.id = id;
    }

    public Stock(long productID, long brandID, long storeID, long providerID, BigDecimal actualAmount, BigDecimal totalAmount, Date createdDate, Date lastModifiedDate) {
        this.productID = productID;
        this.brandID = brandID;
        this.storeID = storeID;
        this.providerID = providerID;
        this.actualAmount = actualAmount;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Stock(Long id, long productID, long brandID, long storeID, long providerID, BigDecimal actualAmount, BigDecimal totalAmount, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.productID = productID;
        this.brandID = brandID;
        this.storeID = storeID;
        this.providerID = providerID;
        this.actualAmount = actualAmount;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getBrandID() {
        return brandID;
    }

    public void setBrandID(long brandID) {
        this.brandID = brandID;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public long getProviderID() {
        return providerID;
    }

    public void setProviderID(long providerID) {
        this.providerID = providerID;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.Stock[ id=" + id + " ]";
    }
    
}
