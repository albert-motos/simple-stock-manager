/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
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
    @NamedQuery(name = "AnalyticsTime.findByLastModifiedDate", query = "SELECT a FROM AnalyticsTime a WHERE a.lastModifiedDate = :lastModifiedDate")})
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
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analitycsTime")
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analitycsTime")
    private List<Invoice> invoiceList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;

    public AnalyticsTime() {
    }

    public AnalyticsTime(Long id) {
        this.id = id;
    }

    public AnalyticsTime(Long id, Date fullTime, int minuteTime, int hourTime, int dayTime, int monthTime, int yearTime, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.fullTime = fullTime;
        this.minuteTime = minuteTime;
        this.hourTime = hourTime;
        this.dayTime = dayTime;
        this.monthTime = monthTime;
        this.yearTime = yearTime;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.fullTime);
        hash = 73 * hash + this.minuteTime;
        hash = 73 * hash + this.hourTime;
        hash = 73 * hash + this.dayTime;
        hash = 73 * hash + this.monthTime;
        hash = 73 * hash + this.yearTime;
        hash = 73 * hash + Objects.hashCode(this.createdDate);
        hash = 73 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 73 * hash + Objects.hashCode(this.createdUser);
        hash = 73 * hash + Objects.hashCode(this.lastModifiedUser);
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
        final AnalyticsTime other = (AnalyticsTime) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fullTime, other.fullTime)) {
            return false;
        }
        if (this.minuteTime != other.minuteTime) {
            return false;
        }
        if (this.hourTime != other.hourTime) {
            return false;
        }
        if (this.dayTime != other.dayTime) {
            return false;
        }
        if (this.monthTime != other.monthTime) {
            return false;
        }
        if (this.yearTime != other.yearTime) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        if (!Objects.equals(this.createdUser, other.createdUser)) {
            return false;
        }
        return Objects.equals(this.lastModifiedUser, other.lastModifiedUser);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.AnalyticsTime[ id=" + id + " ]";
    }
    
}
