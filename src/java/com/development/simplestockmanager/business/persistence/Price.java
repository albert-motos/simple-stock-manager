/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PRICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
    @NamedQuery(name = "Price.findById", query = "SELECT p FROM Price p WHERE p.id = :id"),
    @NamedQuery(name = "Price.findByTitle", query = "SELECT p FROM Price p WHERE p.title = :title"),
    @NamedQuery(name = "Price.findByCost", query = "SELECT p FROM Price p WHERE p.cost = :cost"),
    @NamedQuery(name = "Price.findByInitialAmount", query = "SELECT p FROM Price p WHERE p.initialAmount = :initialAmount"),
    @NamedQuery(name = "Price.findByEndAmount", query = "SELECT p FROM Price p WHERE p.endAmount = :endAmount"),
    @NamedQuery(name = "Price.findByInitialDate", query = "SELECT p FROM Price p WHERE p.initialDate = :initialDate"),
    @NamedQuery(name = "Price.findByEndDate", query = "SELECT p FROM Price p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Price.findByEnable", query = "SELECT p FROM Price p WHERE p.enable = :enable"),
    @NamedQuery(name = "Price.findByCreatedDate", query = "SELECT p FROM Price p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Price.findByLastModifiedDate", query = "SELECT p FROM Price p WHERE p.lastModifiedDate = :lastModifiedDate")})
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "COST")
    private BigDecimal cost;
    @Column(name = "INITIAL_AMOUNT")
    private BigDecimal initialAmount;
    @Column(name = "END_AMOUNT")
    private BigDecimal endAmount;
    @Column(name = "INITIAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
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
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "PRICE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PriceType priceType;
    @JoinColumn(name = "STOCK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Stock stock;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "price")
    private List<Item> itemList;

    public Price() {
    }

    public Price(Long id) {
        this.id = id;
    }

    public Price(Long id, String title, BigDecimal cost, boolean enable, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.cost = cost;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
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
        return "com.development.simplestockmanager.business.persistence.Price[ id=" + id + " ]";
    }
    
}
