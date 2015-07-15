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
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class ProductTypeTranslationJpaController implements Serializable {

    public ProductTypeTranslationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductTypeTranslation productTypeTranslation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductType reference = productTypeTranslation.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                productTypeTranslation.setReference(reference);
            }
            em.persist(productTypeTranslation);
            if (reference != null) {
                reference.getProductTypeTranslationList().add(productTypeTranslation);
                reference = em.merge(reference);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductTypeTranslation productTypeTranslation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductTypeTranslation persistentProductTypeTranslation = em.find(ProductTypeTranslation.class, productTypeTranslation.getId());
            ProductType referenceOld = persistentProductTypeTranslation.getReference();
            ProductType referenceNew = productTypeTranslation.getReference();
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                productTypeTranslation.setReference(referenceNew);
            }
            productTypeTranslation = em.merge(productTypeTranslation);
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getProductTypeTranslationList().remove(productTypeTranslation);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getProductTypeTranslationList().add(productTypeTranslation);
                referenceNew = em.merge(referenceNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = productTypeTranslation.getId();
                if (findProductTypeTranslation(id) == null) {
                    throw new NonexistentEntityException("The productTypeTranslation with id " + id + " no longer exists.");
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
            ProductTypeTranslation productTypeTranslation;
            try {
                productTypeTranslation = em.getReference(ProductTypeTranslation.class, id);
                productTypeTranslation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productTypeTranslation with id " + id + " no longer exists.", enfe);
            }
            ProductType reference = productTypeTranslation.getReference();
            if (reference != null) {
                reference.getProductTypeTranslationList().remove(productTypeTranslation);
                reference = em.merge(reference);
            }
            em.remove(productTypeTranslation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductTypeTranslation> findProductTypeTranslationEntities() {
        return findProductTypeTranslationEntities(true, -1, -1);
    }

    public List<ProductTypeTranslation> findProductTypeTranslationEntities(int maxResults, int firstResult) {
        return findProductTypeTranslationEntities(false, maxResults, firstResult);
    }

    private List<ProductTypeTranslation> findProductTypeTranslationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductTypeTranslation.class));
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

    public ProductTypeTranslation findProductTypeTranslation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductTypeTranslation.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductTypeTranslationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductTypeTranslation> rt = cq.from(ProductTypeTranslation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
