/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.old;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "AnalyticsTime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalyticsTime.findAll", query = "SELECT a FROM AnalyticsTime a"),
    @NamedQuery(name = "AnalyticsTime.findById", query = "SELECT a FROM AnalyticsTime a WHERE a.id = :id"),
    @NamedQuery(name = "AnalyticsTime.findByMinute", query = "SELECT a FROM AnalyticsTime a WHERE a.minute = :minute"),
    @NamedQuery(name = "AnalyticsTime.findByHour", query = "SELECT a FROM AnalyticsTime a WHERE a.hour = :hour"),
    @NamedQuery(name = "AnalyticsTime.findByDay", query = "SELECT a FROM AnalyticsTime a WHERE a.day = :day"),
    @NamedQuery(name = "AnalyticsTime.findByDayTypeID", query = "SELECT a FROM AnalyticsTime a WHERE a.dayTypeID = :dayTypeID"),
    @NamedQuery(name = "AnalyticsTime.findByMonth", query = "SELECT a FROM AnalyticsTime a WHERE a.month = :month"),
    @NamedQuery(name = "AnalyticsTime.findByMonthTypeID", query = "SELECT a FROM AnalyticsTime a WHERE a.monthTypeID = :monthTypeID"),
    @NamedQuery(name = "AnalyticsTime.findByYear", query = "SELECT a FROM AnalyticsTime a WHERE a.year = :year")})
public class AnalyticsTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Minute")
    private int minute;
    @Basic(optional = false)
    @Column(name = "Hour")
    private int hour;
    @Basic(optional = false)
    @Column(name = "Day")
    private int day;
    @Basic(optional = false)
    @Column(name = "DayTypeID")
    private long dayTypeID;
    @Basic(optional = false)
    @Column(name = "Month")
    private int month;
    @Basic(optional = false)
    @Column(name = "MonthTypeID")
    private long monthTypeID;
    @Basic(optional = false)
    @Column(name = "Year")
    private int year;

    public AnalyticsTime() {
    }

    public AnalyticsTime(Long id) {
        this.id = id;
    }

    public AnalyticsTime(int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.dayTypeID = dayTypeID;
        this.month = month;
        this.monthTypeID = monthTypeID;
        this.year = year;
    }

    public AnalyticsTime(Long id, int minute, int hour, int day, long dayTypeID, int month, long monthTypeID, int year) {
        this.id = id;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.dayTypeID = dayTypeID;
        this.month = month;
        this.monthTypeID = monthTypeID;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getDayTypeID() {
        return dayTypeID;
    }

    public void setDayTypeID(long dayTypeID) {
        this.dayTypeID = dayTypeID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getMonthTypeID() {
        return monthTypeID;
    }

    public void setMonthTypeID(long monthTypeID) {
        this.monthTypeID = monthTypeID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return "com.simplestockmanager.persistence.AnalyticsTime[ id=" + id + " ]";
    }
    
}
