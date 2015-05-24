/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AnalyticsTime.findByAll", query = "SELECT a FROM AnalyticsTime a WHERE a.minute = :minute AND a.hour = :hour AND a.day = :day AND "
            + "a.dayTypeID = :dayTypeID AND a.month = :month AND a.monthTypeID = :monthTypeID AND a.year = :year")})
public class NamedQueryHolder implements Serializable {
    @Id
    private Integer id;
}
