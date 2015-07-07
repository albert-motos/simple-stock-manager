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
import java.util.Objects;
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
    @NamedQuery(name = "Price.findByInitialAMOUNT", query = "SELECT p FROM Price p WHERE p.initialAMOUNT = :initialAMOUNT"),
    @NamedQuery(name = "Price.findByEndAMOUNT", query = "SELECT p FROM Price p WHERE p.endAMOUNT = :endAMOUNT"),
    @NamedQuery(name = "Price.findByInitialDate", query = "SELECT p FROM Price p WHERE p.initialDate = :initialDate"),
    @NamedQuery(name = "Price.findByEndDate", query = "SELECT p FROM Price p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Price.findByEnable", query = "SELECT p FROM Price p WHERE p.enable = :enable"),
    @NamedQuery(name = "Price.findByCreatedDate", query = "SELECT p FROM Price p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Price.findByCreatedUser", query = "SELECT p FROM Price p WHERE p.createdUser = :createdUser"),
    @NamedQuery(name = "Price.findByLastModifiedDate", query = "SELECT p FROM Price p WHERE p.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "Price.findByLastModifiedUser", query = "SELECT p FROM Price p WHERE p.lastModifiedUser = :lastModifiedUser")})
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
    @Column(name = "InitialAMOUNT")
    private BigDecimal initialAMOUNT;
    @Column(name = "EndAMOUNT")
    private BigDecimal endAMOUNT;
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
    @Column(name = "CREATED_USER")
    private String createdUser;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_USER")
    private String lastModifiedUser;
    @JoinColumn(name = "PRICE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PriceType priceType;
    @JoinColumn(name = "STOCK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Stock stock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "price")
    private List<Item> itemList;

    public Price() {
    }

    public Price(Long id) {
        this.id = id;
    }

    public Price(Long id, String title, BigDecimal cost, boolean enable, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.title = title;
        this.cost = cost;
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

    public BigDecimal getInitialAMOUNT() {
        return initialAMOUNT;
    }

    public void setInitialAMOUNT(BigDecimal initialAMOUNT) {
        this.initialAMOUNT = initialAMOUNT;
    }

    public BigDecimal getEndAMOUNT() {
        return endAMOUNT;
    }

    public void setEndAMOUNT(BigDecimal endAMOUNT) {
        this.endAMOUNT = endAMOUNT;
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

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.cost);
        hash = 71 * hash + Objects.hashCode(this.initialAMOUNT);
        hash = 71 * hash + Objects.hashCode(this.endAMOUNT);
        hash = 71 * hash + Objects.hashCode(this.initialDate);
        hash = 71 * hash + Objects.hashCode(this.endDate);
        hash = 71 * hash + (this.enable ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.createdDate);
        hash = 71 * hash + Objects.hashCode(this.createdUser);
        hash = 71 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 71 * hash + Objects.hashCode(this.lastModifiedUser);
        hash = 71 * hash + Objects.hashCode(this.priceType);
        hash = 71 * hash + Objects.hashCode(this.stock);
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
        final Price other = (Price) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.initialAMOUNT, other.initialAMOUNT)) {
            return false;
        }
        if (!Objects.equals(this.endAMOUNT, other.endAMOUNT)) {
            return false;
        }
        if (!Objects.equals(this.initialDate, other.initialDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (this.enable != other.enable) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.createdUser, other.createdUser)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedUser, other.lastModifiedUser)) {
            return false;
        }
        if (!Objects.equals(this.priceType, other.priceType)) {
            return false;
        }
        return Objects.equals(this.stock, other.stock);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.Price[ id=" + id + " ]";
    }
    
}
