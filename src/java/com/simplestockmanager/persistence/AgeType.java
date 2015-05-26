/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.persistence;

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
@Table(name = "AgeType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgeType.findAll", query = "SELECT a FROM AgeType a"),
    @NamedQuery(name = "AgeType.findById", query = "SELECT a FROM AgeType a WHERE a.id = :id"),
    @NamedQuery(name = "AgeType.findByType", query = "SELECT a FROM AgeType a WHERE a.type = :type"),
    @NamedQuery(name = "AgeType.findByFromAge", query = "SELECT a FROM AgeType a WHERE a.fromAge = :fromAge"),
    @NamedQuery(name = "AgeType.findByToAge", query = "SELECT a FROM AgeType a WHERE a.toAge = :toAge")})
public class AgeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "FromAge")
    private int fromAge;
    @Basic(optional = false)
    @Column(name = "ToAge")
    private int toAge;

    public AgeType() {
    }

    public AgeType(Long id) {
        this.id = id;
    }

    public AgeType(String type, int fromAge, int toAge) {
        this.type = type;
        this.fromAge = fromAge;
        this.toAge = toAge;
    }

    public AgeType(Long id, String type, int fromAge, int toAge) {
        this.id = id;
        this.type = type;
        this.fromAge = fromAge;
        this.toAge = toAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFromAge() {
        return fromAge;
    }

    public void setFromAge(int fromAge) {
        this.fromAge = fromAge;
    }

    public int getToAge() {
        return toAge;
    }

    public void setToAge(int toAge) {
        this.toAge = toAge;
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
        if (!(object instanceof AgeType)) {
            return false;
        }
        AgeType other = (AgeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.simplestockmanager.persistence.AgeType[ id=" + id + " ]";
    }
    
}
