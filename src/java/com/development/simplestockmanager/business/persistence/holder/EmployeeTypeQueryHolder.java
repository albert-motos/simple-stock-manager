/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Product object
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "EmployeeType.findAllForSelector", query = "SELECT e FROM EmployeeType e WHERE e.languageType.code = :language AND e.enable = TRUE")
    })
public class EmployeeTypeQueryHolder implements Serializable {

    @Id
    private Integer id;
}
