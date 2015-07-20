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
import com.development.simplestockmanager.business.persistence.LanguageTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class LanguageTranslationJpaController implements Serializable {

    public LanguageTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LanguageTranslation languageTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language language = languageTranslation.getLanguage();
            if (language != null) {
                language = em.getReference(language.getClass(), language.getId());
                languageTranslation.setLanguage(language);
            }
            Language reference = languageTranslation.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                languageTranslation.setReference(reference);
            }
            em.persist(languageTranslation);
            if (language != null) {
                language.getLanguageTranslationList().add(languageTranslation);
                language = em.merge(language);
            }
            if (reference != null) {
                reference.getLanguageTranslationList().add(languageTranslation);
                reference = em.merge(reference);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LanguageTranslation languageTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageTranslation persistentLanguageTranslation = em.find(LanguageTranslation.class, languageTranslation.getId());
            Language languageOld = persistentLanguageTranslation.getLanguage();
            Language languageNew = languageTranslation.getLanguage();
            Language referenceOld = persistentLanguageTranslation.getReference();
            Language referenceNew = languageTranslation.getReference();
            if (languageNew != null) {
                languageNew = em.getReference(languageNew.getClass(), languageNew.getId());
                languageTranslation.setLanguage(languageNew);
            }
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                languageTranslation.setReference(referenceNew);
            }
            languageTranslation = em.merge(languageTranslation);
            if (languageOld != null && !languageOld.equals(languageNew)) {
                languageOld.getLanguageTranslationList().remove(languageTranslation);
                languageOld = em.merge(languageOld);
            }
            if (languageNew != null && !languageNew.equals(languageOld)) {
                languageNew.getLanguageTranslationList().add(languageTranslation);
                languageNew = em.merge(languageNew);
            }
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getLanguageTranslationList().remove(languageTranslation);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getLanguageTranslationList().add(languageTranslation);
                referenceNew = em.merge(referenceNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = languageTranslation.getId();
                if (findLanguageTranslation(id) == null) {
                    throw new NonexistentEntityException("The languageTranslation with id " + id + " no longer exists.");
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
            LanguageTranslation languageTranslation;
            try {
                languageTranslation = em.getReference(LanguageTranslation.class, id);
                languageTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The languageTranslation with id " + id + " no longer exists.", enfe);
            }
            Language language = languageTranslation.getLanguage();
            if (language != null) {
                language.getLanguageTranslationList().remove(languageTranslation);
                language = em.merge(language);
            }
            Language reference = languageTranslation.getReference();
            if (reference != null) {
                reference.getLanguageTranslationList().remove(languageTranslation);
                reference = em.merge(reference);
            }
            em.remove(languageTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LanguageTranslation> findLanguageTranslationEntities() {
        return findLanguageTranslationEntities(true, -1, -1);
    }

    public List<LanguageTranslation> findLanguageTranslationEntities(int maxResults, int firstResult) {
        return findLanguageTranslationEntities(false, maxResults, firstResult);
    }

    private List<LanguageTranslation> findLanguageTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LanguageTranslation.class));
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

    public LanguageTranslation findLanguageTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LanguageTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getLanguageTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LanguageTranslation> rt = cq.from(LanguageTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
