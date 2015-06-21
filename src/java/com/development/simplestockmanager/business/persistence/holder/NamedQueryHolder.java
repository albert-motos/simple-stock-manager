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
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SexType.findAllForSelector", query = "SELECT s FROM SexType s WHERE s.languageType = :language AND s.enable = TRUE"),
    @NamedQuery(name = "SexType.getFindByRefencedType", query = "SELECT s FROM SexType s WHERE s.referencedType IS NULL AND s.type = :type"),
    @NamedQuery(name = "SexType.getFindByRefencedTypeAndLanguage", query = "SELECT s FROM SexType s WHERE s.referencedType.type = :type AND s.languageType.code = :language")})
public class NamedQueryHolder implements Serializable {

    @Id
    private Integer id;
}
