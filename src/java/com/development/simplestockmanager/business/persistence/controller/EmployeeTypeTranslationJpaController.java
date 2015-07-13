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
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class EmployeeTypeTranslationJpaController implements Serializable {

    public EmployeeTypeTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeTypeTranslation employeeTypeTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmployeeType reference = employeeTypeTranslation.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                employeeTypeTranslation.setReference(reference);
            }
            Language language = employeeTypeTranslation.getLanguage();
            if (language != null) {
                language = em.getReference(language.getClass(), language.getId());
                employeeTypeTranslation.setLanguage(language);
            }
            em.persist(employeeTypeTranslation);
            if (reference != null) {
                reference.getEmployeeTypeTranslationList().add(employeeTypeTranslation);
                reference = em.merge(reference);
            }
            if (language != null) {
                language.getEmployeeTypeTranslationList().add(employeeTypeTranslation);
                language = em.merge(language);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeTypeTranslation employeeTypeTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmployeeTypeTranslation persistentEmployeeTypeTranslation = em.find(EmployeeTypeTranslation.class, employeeTypeTranslation.getId());
            EmployeeType referenceOld = persistentEmployeeTypeTranslation.getReference();
            EmployeeType referenceNew = employeeTypeTranslation.getReference();
            Language languageOld = persistentEmployeeTypeTranslation.getLanguage();
            Language languageNew = employeeTypeTranslation.getLanguage();
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                employeeTypeTranslation.setReference(referenceNew);
            }
            if (languageNew != null) {
                languageNew = em.getReference(languageNew.getClass(), languageNew.getId());
                employeeTypeTranslation.setLanguage(languageNew);
            }
            employeeTypeTranslation = em.merge(employeeTypeTranslation);
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getEmployeeTypeTranslationList().remove(employeeTypeTranslation);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getEmployeeTypeTranslationList().add(employeeTypeTranslation);
                referenceNew = em.merge(referenceNew);
            }
            if (languageOld != null && !languageOld.equals(languageNew)) {
                languageOld.getEmployeeTypeTranslationList().remove(employeeTypeTranslation);
                languageOld = em.merge(languageOld);
            }
            if (languageNew != null && !languageNew.equals(languageOld)) {
                languageNew.getEmployeeTypeTranslationList().add(employeeTypeTranslation);
                languageNew = em.merge(languageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employeeTypeTranslation.getId();
                if (findEmployeeTypeTranslation(id) == null) {
                    throw new NonexistentEntityException("The employeeTypeTranslation with id " + id + " no longer exists.");
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
            EmployeeTypeTranslation employeeTypeTranslation;
            try {
                employeeTypeTranslation = em.getReference(EmployeeTypeTranslation.class, id);
                employeeTypeTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeTypeTranslation with id " + id + " no longer exists.", enfe);
            }
            EmployeeType reference = employeeTypeTranslation.getReference();
            if (reference != null) {
                reference.getEmployeeTypeTranslationList().remove(employeeTypeTranslation);
                reference = em.merge(reference);
            }
            Language language = employeeTypeTranslation.getLanguage();
            if (language != null) {
                language.getEmployeeTypeTranslationList().remove(employeeTypeTranslation);
                language = em.merge(language);
            }
            em.remove(employeeTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmployeeTypeTranslation> findEmployeeTypeTranslationEntities() {
        return findEmployeeTypeTranslationEntities(true, -1, -1);
    }

    public List<EmployeeTypeTranslation> findEmployeeTypeTranslationEntities(int maxResults, int firstResult) {
        return findEmployeeTypeTranslationEntities(false, maxResults, firstResult);
    }

    private List<EmployeeTypeTranslation> findEmployeeTypeTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeTypeTranslation.class));
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

    public EmployeeTypeTranslation findEmployeeTypeTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeTypeTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeTypeTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeTypeTranslation> rt = cq.from(EmployeeTypeTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
