/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.Language;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.LanguageTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class LanguageJpaController implements Serializable {

    public LanguageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Language language) {
        if (language.getLanguageTranslationList() == null) {
            language.setLanguageTranslationList(new ArrayList<LanguageTranslation>());
        }
        if (language.getLanguageTranslationList1() == null) {
            language.setLanguageTranslationList1(new ArrayList<LanguageTranslation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<LanguageTranslation> attachedLanguageTranslationList = new ArrayList<LanguageTranslation>();
            for (LanguageTranslation languageTranslationListLanguageTranslationToAttach : language.getLanguageTranslationList()) {
                languageTranslationListLanguageTranslationToAttach = em.getReference(languageTranslationListLanguageTranslationToAttach.getClass(), languageTranslationListLanguageTranslationToAttach.getId());
                attachedLanguageTranslationList.add(languageTranslationListLanguageTranslationToAttach);
            }
            language.setLanguageTranslationList(attachedLanguageTranslationList);
            List<LanguageTranslation> attachedLanguageTranslationList1 = new ArrayList<LanguageTranslation>();
            for (LanguageTranslation languageTranslationList1LanguageTranslationToAttach : language.getLanguageTranslationList1()) {
                languageTranslationList1LanguageTranslationToAttach = em.getReference(languageTranslationList1LanguageTranslationToAttach.getClass(), languageTranslationList1LanguageTranslationToAttach.getId());
                attachedLanguageTranslationList1.add(languageTranslationList1LanguageTranslationToAttach);
            }
            language.setLanguageTranslationList1(attachedLanguageTranslationList1);
            em.persist(language);
            for (LanguageTranslation languageTranslationListLanguageTranslation : language.getLanguageTranslationList()) {
                Language oldLanguageOfLanguageTranslationListLanguageTranslation = languageTranslationListLanguageTranslation.getLanguage();
                languageTranslationListLanguageTranslation.setLanguage(language);
                languageTranslationListLanguageTranslation = em.merge(languageTranslationListLanguageTranslation);
                if (oldLanguageOfLanguageTranslationListLanguageTranslation != null) {
                    oldLanguageOfLanguageTranslationListLanguageTranslation.getLanguageTranslationList().remove(languageTranslationListLanguageTranslation);
                    oldLanguageOfLanguageTranslationListLanguageTranslation = em.merge(oldLanguageOfLanguageTranslationListLanguageTranslation);
                }
            }
            for (LanguageTranslation languageTranslationList1LanguageTranslation : language.getLanguageTranslationList1()) {
                Language oldReferenceOfLanguageTranslationList1LanguageTranslation = languageTranslationList1LanguageTranslation.getReference();
                languageTranslationList1LanguageTranslation.setReference(language);
                languageTranslationList1LanguageTranslation = em.merge(languageTranslationList1LanguageTranslation);
                if (oldReferenceOfLanguageTranslationList1LanguageTranslation != null) {
                    oldReferenceOfLanguageTranslationList1LanguageTranslation.getLanguageTranslationList1().remove(languageTranslationList1LanguageTranslation);
                    oldReferenceOfLanguageTranslationList1LanguageTranslation = em.merge(oldReferenceOfLanguageTranslationList1LanguageTranslation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Language language) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language persistentLanguage = em.find(Language.class, language.getId());
            List<LanguageTranslation> languageTranslationListOld = persistentLanguage.getLanguageTranslationList();
            List<LanguageTranslation> languageTranslationListNew = language.getLanguageTranslationList();
            List<LanguageTranslation> languageTranslationList1Old = persistentLanguage.getLanguageTranslationList1();
            List<LanguageTranslation> languageTranslationList1New = language.getLanguageTranslationList1();
            List<String> illegalOrphanMessages = null;
            for (LanguageTranslation languageTranslationListOldLanguageTranslation : languageTranslationListOld) {
                if (!languageTranslationListNew.contains(languageTranslationListOldLanguageTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LanguageTranslation " + languageTranslationListOldLanguageTranslation + " since its language field is not nullable.");
                }
            }
            for (LanguageTranslation languageTranslationList1OldLanguageTranslation : languageTranslationList1Old) {
                if (!languageTranslationList1New.contains(languageTranslationList1OldLanguageTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LanguageTranslation " + languageTranslationList1OldLanguageTranslation + " since its reference field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<LanguageTranslation> attachedLanguageTranslationListNew = new ArrayList<LanguageTranslation>();
            for (LanguageTranslation languageTranslationListNewLanguageTranslationToAttach : languageTranslationListNew) {
                languageTranslationListNewLanguageTranslationToAttach = em.getReference(languageTranslationListNewLanguageTranslationToAttach.getClass(), languageTranslationListNewLanguageTranslationToAttach.getId());
                attachedLanguageTranslationListNew.add(languageTranslationListNewLanguageTranslationToAttach);
            }
            languageTranslationListNew = attachedLanguageTranslationListNew;
            language.setLanguageTranslationList(languageTranslationListNew);
            List<LanguageTranslation> attachedLanguageTranslationList1New = new ArrayList<LanguageTranslation>();
            for (LanguageTranslation languageTranslationList1NewLanguageTranslationToAttach : languageTranslationList1New) {
                languageTranslationList1NewLanguageTranslationToAttach = em.getReference(languageTranslationList1NewLanguageTranslationToAttach.getClass(), languageTranslationList1NewLanguageTranslationToAttach.getId());
                attachedLanguageTranslationList1New.add(languageTranslationList1NewLanguageTranslationToAttach);
            }
            languageTranslationList1New = attachedLanguageTranslationList1New;
            language.setLanguageTranslationList1(languageTranslationList1New);
            language = em.merge(language);
            for (LanguageTranslation languageTranslationListNewLanguageTranslation : languageTranslationListNew) {
                if (!languageTranslationListOld.contains(languageTranslationListNewLanguageTranslation)) {
                    Language oldLanguageOfLanguageTranslationListNewLanguageTranslation = languageTranslationListNewLanguageTranslation.getLanguage();
                    languageTranslationListNewLanguageTranslation.setLanguage(language);
                    languageTranslationListNewLanguageTranslation = em.merge(languageTranslationListNewLanguageTranslation);
                    if (oldLanguageOfLanguageTranslationListNewLanguageTranslation != null && !oldLanguageOfLanguageTranslationListNewLanguageTranslation.equals(language)) {
                        oldLanguageOfLanguageTranslationListNewLanguageTranslation.getLanguageTranslationList().remove(languageTranslationListNewLanguageTranslation);
                        oldLanguageOfLanguageTranslationListNewLanguageTranslation = em.merge(oldLanguageOfLanguageTranslationListNewLanguageTranslation);
                    }
                }
            }
            for (LanguageTranslation languageTranslationList1NewLanguageTranslation : languageTranslationList1New) {
                if (!languageTranslationList1Old.contains(languageTranslationList1NewLanguageTranslation)) {
                    Language oldReferenceOfLanguageTranslationList1NewLanguageTranslation = languageTranslationList1NewLanguageTranslation.getReference();
                    languageTranslationList1NewLanguageTranslation.setReference(language);
                    languageTranslationList1NewLanguageTranslation = em.merge(languageTranslationList1NewLanguageTranslation);
                    if (oldReferenceOfLanguageTranslationList1NewLanguageTranslation != null && !oldReferenceOfLanguageTranslationList1NewLanguageTranslation.equals(language)) {
                        oldReferenceOfLanguageTranslationList1NewLanguageTranslation.getLanguageTranslationList1().remove(languageTranslationList1NewLanguageTranslation);
                        oldReferenceOfLanguageTranslationList1NewLanguageTranslation = em.merge(oldReferenceOfLanguageTranslationList1NewLanguageTranslation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = language.getId();
                if (findLanguage(id) == null) {
                    throw new NonexistentEntityException("The language with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language language;
            try {
                language = em.getReference(Language.class, id);
                language.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The language with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<LanguageTranslation> languageTranslationListOrphanCheck = language.getLanguageTranslationList();
            for (LanguageTranslation languageTranslationListOrphanCheckLanguageTranslation : languageTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the LanguageTranslation " + languageTranslationListOrphanCheckLanguageTranslation + " in its languageTranslationList field has a non-nullable language field.");
            }
            List<LanguageTranslation> languageTranslationList1OrphanCheck = language.getLanguageTranslationList1();
            for (LanguageTranslation languageTranslationList1OrphanCheckLanguageTranslation : languageTranslationList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the LanguageTranslation " + languageTranslationList1OrphanCheckLanguageTranslation + " in its languageTranslationList1 field has a non-nullable reference field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(language);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Language> findLanguageEntities() {
        return findLanguageEntities(true, -1, -1);
    }

    public List<Language> findLanguageEntities(int maxResults, int firstResult) {
        return findLanguageEntities(false, maxResults, firstResult);
    }

    private List<Language> findLanguageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Language.class));
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

    public Language findLanguage(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Language.class, id);
        } finally {
            em.close();
        }
    }

    public int getLanguageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Language> rt = cq.from(Language.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
