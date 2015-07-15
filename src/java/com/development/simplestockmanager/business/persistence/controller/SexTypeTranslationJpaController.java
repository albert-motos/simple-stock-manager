/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
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
public class SexTypeTranslationJpaController implements Serializable {

    public SexTypeTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SexTypeTranslation sexTypeTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sexTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SexTypeTranslation sexTypeTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sexTypeTranslation = em.merge(sexTypeTranslation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sexTypeTranslation.getId();
                if (findSexTypeTranslation(id) == null) {
                    throw new NonexistentEntityException("The sexTypeTranslation with id " + id + " no longer exists.");
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
            SexTypeTranslation sexTypeTranslation;
            try {
                sexTypeTranslation = em.getReference(SexTypeTranslation.class, id);
                sexTypeTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexTypeTranslation with id " + id + " no longer exists.", enfe);
            }
            em.remove(sexTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SexTypeTranslation> findSexTypeTranslationEntities() {
        return findSexTypeTranslationEntities(true, -1, -1);
    }

    public List<SexTypeTranslation> findSexTypeTranslationEntities(int maxResults, int firstResult) {
        return findSexTypeTranslationEntities(false, maxResults, firstResult);
    }

    private List<SexTypeTranslation> findSexTypeTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SexTypeTranslation.class));
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

    public SexTypeTranslation findSexTypeTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SexTypeTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexTypeTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SexTypeTranslation> rt = cq.from(SexTypeTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
