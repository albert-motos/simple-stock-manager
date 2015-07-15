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
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
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
        if (language.getLanguageList() == null) {
            language.setLanguageList(new ArrayList<Language>());
        }
        if (language.getEmployeeList() == null) {
            language.setEmployeeList(new ArrayList<Employee>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language reference = language.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                language.setReference(reference);
            }
            List<Language> attachedLanguageList = new ArrayList<Language>();
            for (Language languageListLanguageToAttach : language.getLanguageList()) {
                languageListLanguageToAttach = em.getReference(languageListLanguageToAttach.getClass(), languageListLanguageToAttach.getId());
                attachedLanguageList.add(languageListLanguageToAttach);
            }
            language.setLanguageList(attachedLanguageList);
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : language.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            language.setEmployeeList(attachedEmployeeList);
            em.persist(language);
            if (reference != null) {
                reference.getLanguageList().add(language);
                reference = em.merge(reference);
            }
            for (Language languageListLanguage : language.getLanguageList()) {
                Language oldReferenceOfLanguageListLanguage = languageListLanguage.getReference();
                languageListLanguage.setReference(language);
                languageListLanguage = em.merge(languageListLanguage);
                if (oldReferenceOfLanguageListLanguage != null) {
                    oldReferenceOfLanguageListLanguage.getLanguageList().remove(languageListLanguage);
                    oldReferenceOfLanguageListLanguage = em.merge(oldReferenceOfLanguageListLanguage);
                }
            }
            for (Employee employeeListEmployee : language.getEmployeeList()) {
                Language oldLanguageOfEmployeeListEmployee = employeeListEmployee.getLanguage();
                employeeListEmployee.setLanguage(language);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldLanguageOfEmployeeListEmployee != null) {
                    oldLanguageOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldLanguageOfEmployeeListEmployee = em.merge(oldLanguageOfEmployeeListEmployee);
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
            Language referenceOld = persistentLanguage.getReference();
            Language referenceNew = language.getReference();
            List<Language> languageListOld = persistentLanguage.getLanguageList();
            List<Language> languageListNew = language.getLanguageList();
            List<Employee> employeeListOld = persistentLanguage.getEmployeeList();
            List<Employee> employeeListNew = language.getEmployeeList();
            List<String> illegalOrphanMessages = null;
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its language field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                language.setReference(referenceNew);
            }
            List<Language> attachedLanguageListNew = new ArrayList<Language>();
            for (Language languageListNewLanguageToAttach : languageListNew) {
                languageListNewLanguageToAttach = em.getReference(languageListNewLanguageToAttach.getClass(), languageListNewLanguageToAttach.getId());
                attachedLanguageListNew.add(languageListNewLanguageToAttach);
            }
            languageListNew = attachedLanguageListNew;
            language.setLanguageList(languageListNew);
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            language.setEmployeeList(employeeListNew);
            language = em.merge(language);
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getLanguageList().remove(language);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getLanguageList().add(language);
                referenceNew = em.merge(referenceNew);
            }
            for (Language languageListOldLanguage : languageListOld) {
                if (!languageListNew.contains(languageListOldLanguage)) {
                    languageListOldLanguage.setReference(null);
                    languageListOldLanguage = em.merge(languageListOldLanguage);
                }
            }
            for (Language languageListNewLanguage : languageListNew) {
                if (!languageListOld.contains(languageListNewLanguage)) {
                    Language oldReferenceOfLanguageListNewLanguage = languageListNewLanguage.getReference();
                    languageListNewLanguage.setReference(language);
                    languageListNewLanguage = em.merge(languageListNewLanguage);
                    if (oldReferenceOfLanguageListNewLanguage != null && !oldReferenceOfLanguageListNewLanguage.equals(language)) {
                        oldReferenceOfLanguageListNewLanguage.getLanguageList().remove(languageListNewLanguage);
                        oldReferenceOfLanguageListNewLanguage = em.merge(oldReferenceOfLanguageListNewLanguage);
                    }
                }
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    Language oldLanguageOfEmployeeListNewEmployee = employeeListNewEmployee.getLanguage();
                    employeeListNewEmployee.setLanguage(language);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldLanguageOfEmployeeListNewEmployee != null && !oldLanguageOfEmployeeListNewEmployee.equals(language)) {
                        oldLanguageOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldLanguageOfEmployeeListNewEmployee = em.merge(oldLanguageOfEmployeeListNewEmployee);
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
            List<Employee> employeeListOrphanCheck = language.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable language field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Language reference = language.getReference();
            if (reference != null) {
                reference.getLanguageList().remove(language);
                reference = em.merge(reference);
            }
            List<Language> languageList = language.getLanguageList();
            for (Language languageListLanguage : languageList) {
                languageListLanguage.setReference(null);
                languageListLanguage = em.merge(languageListLanguage);
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
