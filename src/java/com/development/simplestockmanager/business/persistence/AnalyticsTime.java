/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Monica
 */
@Entity
@Table(name = "ANALYTICS_TIME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalyticsTime.findAll", query = "SELECT a FROM AnalyticsTime a"),
    @NamedQuery(name = "AnalyticsTime.findById", query = "SELECT a FROM AnalyticsTime a WHERE a.id = :id"),
    @NamedQuery(name = "AnalyticsTime.findByFullTime", query = "SELECT a FROM AnalyticsTime a WHERE a.fullTime = :fullTime"),
    @NamedQuery(name = "AnalyticsTime.findByMinuteTime", query = "SELECT a FROM AnalyticsTime a WHERE a.minuteTime = :minuteTime"),
    @NamedQuery(name = "AnalyticsTime.findByHourTime", query = "SELECT a FROM AnalyticsTime a WHERE a.hourTime = :hourTime"),
    @NamedQuery(name = "AnalyticsTime.findByDayTime", query = "SELECT a FROM AnalyticsTime a WHERE a.dayTime = :dayTime"),
    @NamedQuery(name = "AnalyticsTime.findByMonthTime", query = "SELECT a FROM AnalyticsTime a WHERE a.monthTime = :monthTime"),
    @NamedQuery(name = "AnalyticsTime.findByYearTime", query = "SELECT a FROM AnalyticsTime a WHERE a.yearTime = :yearTime"),
    @NamedQuery(name = "AnalyticsTime.findByCreatedDate", query = "SELECT a FROM AnalyticsTime a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "AnalyticsTime.findByCreatedUser", query = "SELECT a FROM AnalyticsTime a WHERE a.createdUser = :createdUser"),
    @NamedQuery(name = "AnalyticsTime.findByLastModifiedDate", query = "SELECT a FROM AnalyticsTime a WHERE a.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "AnalyticsTime.findByLastModifiedUser", query = "SELECT a FROM AnalyticsTime a WHERE a.lastModifiedUser = :lastModifiedUser")})
public class AnalyticsTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FULL_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fullTime;
    @Basic(optional = false)
    @Column(name = "MINUTE_TIME")
    private int minuteTime;
    @Basic(optional = false)
    @Column(name = "HOUR_TIME")
    private int hourTime;
    @Basic(optional = false)
    @Column(name = "DAY_TIME")
    private int dayTime;
    @Basic(optional = false)
    @Column(name = "MONTH_TIME")
    private int monthTime;
    @Basic(optional = false)
    @Column(name = "YEAR_TIME")
    private int yearTime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analitycsTime")
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analitycsTime")
    private List<Invoice> invoiceList;

    public AnalyticsTime() {
    }

    public AnalyticsTime(Long id) {
        this.id = id;
    }

    public AnalyticsTime(Long id, Date fullTime, int minuteTime, int hourTime, int dayTime, int monthTime, int yearTime, Date createdDate, String createdUser, Date lastModifiedDate, String lastModifiedUser) {
        this.id = id;
        this.fullTime = fullTime;
        this.minuteTime = minuteTime;
        this.hourTime = hourTime;
        this.dayTime = dayTime;
        this.monthTime = monthTime;
        this.yearTime = yearTime;
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

    public Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    public int getMinuteTime() {
        return minuteTime;
    }

    public void setMinuteTime(int minuteTime) {
        this.minuteTime = minuteTime;
    }

    public int getHourTime() {
        return hourTime;
    }

    public void setHourTime(int hourTime) {
        this.hourTime = hourTime;
    }

    public int getDayTime() {
        return dayTime;
    }

    public void setDayTime(int dayTime) {
        this.dayTime = dayTime;
    }

    public int getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(int monthTime) {
        this.monthTime = monthTime;
    }

    public int getYearTime() {
        return yearTime;
    }

    public void setYearTime(int yearTime) {
        this.yearTime = yearTime;
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

    @XmlTransient
    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
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
        if (!(object instanceof AnalyticsTime)) {
            return false;
        }
        AnalyticsTime other = (AnalyticsTime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB.AnalyticsTime[ id=" + id + " ]";
    }
    
}
