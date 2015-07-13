/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class PriceTypeTranslationJpaController implements Serializable {

    public PriceTypeTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PriceTypeTranslation priceTypeTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language language = priceTypeTranslation.getLanguage();
            if (language != null) {
                language = em.getReference(language.getClass(), language.getId());
                priceTypeTranslation.setLanguage(language);
            }
            PriceType reference = priceTypeTranslation.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                priceTypeTranslation.setReference(reference);
            }
            em.persist(priceTypeTranslation);
            if (language != null) {
                language.getPriceTypeTranslationList().add(priceTypeTranslation);
                language = em.merge(language);
            }
            if (reference != null) {
                reference.getPriceTypeTranslationList().add(priceTypeTranslation);
                reference = em.merge(reference);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PriceTypeTranslation priceTypeTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PriceTypeTranslation persistentPriceTypeTranslation = em.find(PriceTypeTranslation.class, priceTypeTranslation.getId());
            Language languageOld = persistentPriceTypeTranslation.getLanguage();
            Language languageNew = priceTypeTranslation.getLanguage();
            PriceType referenceOld = persistentPriceTypeTranslation.getReference();
            PriceType referenceNew = priceTypeTranslation.getReference();
            if (languageNew != null) {
                languageNew = em.getReference(languageNew.getClass(), languageNew.getId());
                priceTypeTranslation.setLanguage(languageNew);
            }
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                priceTypeTranslation.setReference(referenceNew);
            }
            priceTypeTranslation = em.merge(priceTypeTranslation);
            if (languageOld != null && !languageOld.equals(languageNew)) {
                languageOld.getPriceTypeTranslationList().remove(priceTypeTranslation);
                languageOld = em.merge(languageOld);
            }
            if (languageNew != null && !languageNew.equals(languageOld)) {
                languageNew.getPriceTypeTranslationList().add(priceTypeTranslation);
                languageNew = em.merge(languageNew);
            }
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getPriceTypeTranslationList().remove(priceTypeTranslation);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getPriceTypeTranslationList().add(priceTypeTranslation);
                referenceNew = em.merge(referenceNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = priceTypeTranslation.getId();
                if (findPriceTypeTranslation(id) == null) {
                    throw new NonexistentEntityException("The priceTypeTranslation with id " + id + " no longer exists.");
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
            PriceTypeTranslation priceTypeTranslation;
            try {
                priceTypeTranslation = em.getReference(PriceTypeTranslation.class, id);
                priceTypeTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The priceTypeTranslation with id " + id + " no longer exists.", enfe);
            }
            Language language = priceTypeTranslation.getLanguage();
            if (language != null) {
                language.getPriceTypeTranslationList().remove(priceTypeTranslation);
                language = em.merge(language);
            }
            PriceType reference = priceTypeTranslation.getReference();
            if (reference != null) {
                reference.getPriceTypeTranslationList().remove(priceTypeTranslation);
                reference = em.merge(reference);
            }
            em.remove(priceTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PriceTypeTranslation> findPriceTypeTranslationEntities() {
        return findPriceTypeTranslationEntities(true, -1, -1);
    }

    public List<PriceTypeTranslation> findPriceTypeTranslationEntities(int maxResults, int firstResult) {
        return findPriceTypeTranslationEntities(false, maxResults, firstResult);
    }

    private List<PriceTypeTranslation> findPriceTypeTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PriceTypeTranslation.class));
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

    public PriceTypeTranslation findPriceTypeTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PriceTypeTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getPriceTypeTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PriceTypeTranslation> rt = cq.from(PriceTypeTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
