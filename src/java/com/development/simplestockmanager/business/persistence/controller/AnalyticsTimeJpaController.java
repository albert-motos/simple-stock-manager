/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.AnalyticsTime;
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
public class AnalyticsTimeJpaController implements Serializable {

    public AnalyticsTimeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnalyticsTime analyticsTime) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(analyticsTime);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnalyticsTime analyticsTime) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            analyticsTime = em.merge(analyticsTime);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = analyticsTime.getId();
                if (findAnalyticsTime(id) == null) {
                    throw new NonexistentEntityException("The analyticsTime with id " + id + " no longer exists.");
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
            AnalyticsTime analyticsTime;
            try {
                analyticsTime = em.getReference(AnalyticsTime.class, id);
                analyticsTime.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analyticsTime with id " + id + " no longer exists.", enfe);
            }
            em.remove(analyticsTime);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnalyticsTime> findAnalyticsTimeEntities() {
        return findAnalyticsTimeEntities(true, -1, -1);
    }

    public List<AnalyticsTime> findAnalyticsTimeEntities(int maxResults, int firstResult) {
        return findAnalyticsTimeEntities(false, maxResults, firstResult);
    }

    private List<AnalyticsTime> findAnalyticsTimeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnalyticsTime.class));
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

    public AnalyticsTime findAnalyticsTime(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnalyticsTime.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalyticsTimeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnalyticsTime> rt = cq.from(AnalyticsTime.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
