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
@Table(name = "EmployeeType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeType.findAll", query = "SELECT e FROM EmployeeType e"),
    @NamedQuery(name = "EmployeeType.findById", query = "SELECT e FROM EmployeeType e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeeType.findByType", query = "SELECT e FROM EmployeeType e WHERE e.type = :type")})
public class EmployeeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;

    public EmployeeType() {
    }

    public EmployeeType(Long id) {
        this.id = id;
    }

    public EmployeeType(String type) {
        this.type = type;
    }

    public EmployeeType(Long id, String type) {
        this.id = id;
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeType)) {
            return false;
        }
        EmployeeType other = (EmployeeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type;
    }
    
}
