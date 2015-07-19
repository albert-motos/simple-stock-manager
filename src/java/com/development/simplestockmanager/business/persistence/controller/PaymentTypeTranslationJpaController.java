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
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class PaymentTypeTranslationJpaController implements Serializable {

    public PaymentTypeTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentTypeTranslation paymentTypeTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language language = paymentTypeTranslation.getLanguage();
            if (language != null) {
                language = em.getReference(language.getClass(), language.getId());
                paymentTypeTranslation.setLanguage(language);
            }
            PaymentType reference = paymentTypeTranslation.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                paymentTypeTranslation.setReference(reference);
            }
            em.persist(paymentTypeTranslation);
            if (language != null) {
                language.getPaymentTypeTranslationList().add(paymentTypeTranslation);
                language = em.merge(language);
            }
            if (reference != null) {
                reference.getPaymentTypeTranslationList().add(paymentTypeTranslation);
                reference = em.merge(reference);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaymentTypeTranslation paymentTypeTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaymentTypeTranslation persistentPaymentTypeTranslation = em.find(PaymentTypeTranslation.class, paymentTypeTranslation.getId());
            Language languageOld = persistentPaymentTypeTranslation.getLanguage();
            Language languageNew = paymentTypeTranslation.getLanguage();
            PaymentType referenceOld = persistentPaymentTypeTranslation.getReference();
            PaymentType referenceNew = paymentTypeTranslation.getReference();
            if (languageNew != null) {
                languageNew = em.getReference(languageNew.getClass(), languageNew.getId());
                paymentTypeTranslation.setLanguage(languageNew);
            }
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                paymentTypeTranslation.setReference(referenceNew);
            }
            paymentTypeTranslation = em.merge(paymentTypeTranslation);
            if (languageOld != null && !languageOld.equals(languageNew)) {
                languageOld.getPaymentTypeTranslationList().remove(paymentTypeTranslation);
                languageOld = em.merge(languageOld);
            }
            if (languageNew != null && !languageNew.equals(languageOld)) {
                languageNew.getPaymentTypeTranslationList().add(paymentTypeTranslation);
                languageNew = em.merge(languageNew);
            }
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getPaymentTypeTranslationList().remove(paymentTypeTranslation);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getPaymentTypeTranslationList().add(paymentTypeTranslation);
                referenceNew = em.merge(referenceNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paymentTypeTranslation.getId();
                if (findPaymentTypeTranslation(id) == null) {
                    throw new NonexistentEntityException("The paymentTypeTranslation with id " + id + " no longer exists.");
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
            PaymentTypeTranslation paymentTypeTranslation;
            try {
                paymentTypeTranslation = em.getReference(PaymentTypeTranslation.class, id);
                paymentTypeTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentTypeTranslation with id " + id + " no longer exists.", enfe);
            }
            Language language = paymentTypeTranslation.getLanguage();
            if (language != null) {
                language.getPaymentTypeTranslationList().remove(paymentTypeTranslation);
                language = em.merge(language);
            }
            PaymentType reference = paymentTypeTranslation.getReference();
            if (reference != null) {
                reference.getPaymentTypeTranslationList().remove(paymentTypeTranslation);
                reference = em.merge(reference);
            }
            em.remove(paymentTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaymentTypeTranslation> findPaymentTypeTranslationEntities() {
        return findPaymentTypeTranslationEntities(true, -1, -1);
    }

    public List<PaymentTypeTranslation> findPaymentTypeTranslationEntities(int maxResults, int firstResult) {
        return findPaymentTypeTranslationEntities(false, maxResults, firstResult);
    }

    private List<PaymentTypeTranslation> findPaymentTypeTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaymentTypeTranslation.class));
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

    public PaymentTypeTranslation findPaymentTypeTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentTypeTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentTypeTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaymentTypeTranslation> rt = cq.from(PaymentTypeTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
