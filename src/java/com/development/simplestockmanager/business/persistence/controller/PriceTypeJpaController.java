/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.PriceType;
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
public class PriceTypeJpaController implements Serializable {

    public PriceTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PriceType priceType) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(priceType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PriceType priceType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            priceType = em.merge(priceType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = priceType.getId();
                if (findPriceType(id) == null) {
                    throw new NonexistentEntityException("The priceType with id " + id + " no longer exists.");
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
            PriceType priceType;
            try {
                priceType = em.getReference(PriceType.class, id);
                priceType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The priceType with id " + id + " no longer exists.", enfe);
            }
            em.remove(priceType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PriceType> findPriceTypeEntities() {
        return findPriceTypeEntities(true, -1, -1);
    }

    public List<PriceType> findPriceTypeEntities(int maxResults, int firstResult) {
        return findPriceTypeEntities(false, maxResults, firstResult);
    }

    private List<PriceType> findPriceTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PriceType.class));
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

    public PriceType findPriceType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PriceType.class, id);
        } finally {
            em.close();
        }
    }

    public int getPriceTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PriceType> rt = cq.from(PriceType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
