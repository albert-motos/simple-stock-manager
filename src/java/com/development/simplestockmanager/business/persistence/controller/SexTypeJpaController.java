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
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class SexTypeJpaController implements Serializable {

    public SexTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SexType sexType) {
        if (sexType.getSexTypeList() == null) {
            sexType.setSexTypeList(new ArrayList<SexType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageType languageType = sexType.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                sexType.setLanguageType(languageType);
            }
            SexType referencedType = sexType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                sexType.setReferencedType(referencedType);
            }
            List<SexType> attachedSexTypeList = new ArrayList<SexType>();
            for (SexType sexTypeListSexTypeToAttach : sexType.getSexTypeList()) {
                sexTypeListSexTypeToAttach = em.getReference(sexTypeListSexTypeToAttach.getClass(), sexTypeListSexTypeToAttach.getId());
                attachedSexTypeList.add(sexTypeListSexTypeToAttach);
            }
            sexType.setSexTypeList(attachedSexTypeList);
            em.persist(sexType);
            if (languageType != null) {
                languageType.getSexTypeList().add(sexType);
                languageType = em.merge(languageType);
            }
            if (referencedType != null) {
                referencedType.getSexTypeList().add(sexType);
                referencedType = em.merge(referencedType);
            }
            for (SexType sexTypeListSexType : sexType.getSexTypeList()) {
                SexType oldReferencedTypeOfSexTypeListSexType = sexTypeListSexType.getReferencedType();
                sexTypeListSexType.setReferencedType(sexType);
                sexTypeListSexType = em.merge(sexTypeListSexType);
                if (oldReferencedTypeOfSexTypeListSexType != null) {
                    oldReferencedTypeOfSexTypeListSexType.getSexTypeList().remove(sexTypeListSexType);
                    oldReferencedTypeOfSexTypeListSexType = em.merge(oldReferencedTypeOfSexTypeListSexType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SexType sexType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SexType persistentSexType = em.find(SexType.class, sexType.getId());
            LanguageType languageTypeOld = persistentSexType.getLanguageType();
            LanguageType languageTypeNew = sexType.getLanguageType();
            SexType referencedTypeOld = persistentSexType.getReferencedType();
            SexType referencedTypeNew = sexType.getReferencedType();
            List<SexType> sexTypeListOld = persistentSexType.getSexTypeList();
            List<SexType> sexTypeListNew = sexType.getSexTypeList();
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                sexType.setLanguageType(languageTypeNew);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                sexType.setReferencedType(referencedTypeNew);
            }
            List<SexType> attachedSexTypeListNew = new ArrayList<SexType>();
            for (SexType sexTypeListNewSexTypeToAttach : sexTypeListNew) {
                sexTypeListNewSexTypeToAttach = em.getReference(sexTypeListNewSexTypeToAttach.getClass(), sexTypeListNewSexTypeToAttach.getId());
                attachedSexTypeListNew.add(sexTypeListNewSexTypeToAttach);
            }
            sexTypeListNew = attachedSexTypeListNew;
            sexType.setSexTypeList(sexTypeListNew);
            sexType = em.merge(sexType);
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getSexTypeList().remove(sexType);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getSexTypeList().add(sexType);
                languageTypeNew = em.merge(languageTypeNew);
            }
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getSexTypeList().remove(sexType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getSexTypeList().add(sexType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    sexTypeListOldSexType.setReferencedType(null);
                    sexTypeListOldSexType = em.merge(sexTypeListOldSexType);
                }
            }
            for (SexType sexTypeListNewSexType : sexTypeListNew) {
                if (!sexTypeListOld.contains(sexTypeListNewSexType)) {
                    SexType oldReferencedTypeOfSexTypeListNewSexType = sexTypeListNewSexType.getReferencedType();
                    sexTypeListNewSexType.setReferencedType(sexType);
                    sexTypeListNewSexType = em.merge(sexTypeListNewSexType);
                    if (oldReferencedTypeOfSexTypeListNewSexType != null && !oldReferencedTypeOfSexTypeListNewSexType.equals(sexType)) {
                        oldReferencedTypeOfSexTypeListNewSexType.getSexTypeList().remove(sexTypeListNewSexType);
                        oldReferencedTypeOfSexTypeListNewSexType = em.merge(oldReferencedTypeOfSexTypeListNewSexType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sexType.getId();
                if (findSexType(id) == null) {
                    throw new NonexistentEntityException("The sexType with id " + id + " no longer exists.");
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
            SexType sexType;
            try {
                sexType = em.getReference(SexType.class, id);
                sexType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexType with id " + id + " no longer exists.", enfe);
            }
            LanguageType languageType = sexType.getLanguageType();
            if (languageType != null) {
                languageType.getSexTypeList().remove(sexType);
                languageType = em.merge(languageType);
            }
            SexType referencedType = sexType.getReferencedType();
            if (referencedType != null) {
                referencedType.getSexTypeList().remove(sexType);
                referencedType = em.merge(referencedType);
            }
            List<SexType> sexTypeList = sexType.getSexTypeList();
            for (SexType sexTypeListSexType : sexTypeList) {
                sexTypeListSexType.setReferencedType(null);
                sexTypeListSexType = em.merge(sexTypeListSexType);
            }
            em.remove(sexType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SexType> findSexTypeEntities() {
        return findSexTypeEntities(true, -1, -1);
    }

    public List<SexType> findSexTypeEntities(int maxResults, int firstResult) {
        return findSexTypeEntities(false, maxResults, firstResult);
    }

    private List<SexType> findSexTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SexType.class));
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

    public SexType findSexType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SexType.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SexType> rt = cq.from(SexType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
