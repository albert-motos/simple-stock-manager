/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author foxtrot
 */
public class EmployeeTypeJpaController implements Serializable {

    public EmployeeTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeType employeeType) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(employeeType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeType employeeType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            employeeType = em.merge(employeeType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employeeType.getId();
                if (findEmployeeType(id) == null) {
                    throw new NonexistentEntityException("The employeeType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmployeeType employeeType;
            try {
                employeeType = em.getReference(EmployeeType.class, id);
                employeeType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeType with id " + id + " no longer exists.", enfe);
            }
            em.remove(employeeType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmployeeType> findEmployeeTypeEntities() {
        return findEmployeeTypeEntities(true, -1, -1);
    }

    public List<EmployeeType> findEmployeeTypeEntities(int maxResults, int firstResult) {
        return findEmployeeTypeEntities(false, maxResults, firstResult);
    }

    private List<EmployeeType> findEmployeeTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeType.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EmployeeType findEmployeeType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeType.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeType> rt = cq.from(EmployeeType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
