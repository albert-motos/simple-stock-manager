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
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.PriceType;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (priceType.getPriceList() == null) {
            priceType.setPriceList(new ArrayList<Price>());
        }
        if (priceType.getPriceTypeTranslationList() == null) {
            priceType.setPriceTypeTranslationList(new ArrayList<PriceTypeTranslation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = priceType.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                priceType.setCreatedUser(createdUser);
            }
            Employee lastModifiedUser = priceType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                priceType.setLastModifiedUser(lastModifiedUser);
            }
            List<Price> attachedPriceList = new ArrayList<Price>();
            for (Price priceListPriceToAttach : priceType.getPriceList()) {
                priceListPriceToAttach = em.getReference(priceListPriceToAttach.getClass(), priceListPriceToAttach.getId());
                attachedPriceList.add(priceListPriceToAttach);
            }
            priceType.setPriceList(attachedPriceList);
            List<PriceTypeTranslation> attachedPriceTypeTranslationList = new ArrayList<PriceTypeTranslation>();
            for (PriceTypeTranslation priceTypeTranslationListPriceTypeTranslationToAttach : priceType.getPriceTypeTranslationList()) {
                priceTypeTranslationListPriceTypeTranslationToAttach = em.getReference(priceTypeTranslationListPriceTypeTranslationToAttach.getClass(), priceTypeTranslationListPriceTypeTranslationToAttach.getId());
                attachedPriceTypeTranslationList.add(priceTypeTranslationListPriceTypeTranslationToAttach);
            }
            priceType.setPriceTypeTranslationList(attachedPriceTypeTranslationList);
            em.persist(priceType);
            if (createdUser != null) {
                createdUser.getPriceTypeList().add(priceType);
                createdUser = em.merge(createdUser);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getPriceTypeList().add(priceType);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (Price priceListPrice : priceType.getPriceList()) {
                PriceType oldPriceTypeOfPriceListPrice = priceListPrice.getPriceType();
                priceListPrice.setPriceType(priceType);
                priceListPrice = em.merge(priceListPrice);
                if (oldPriceTypeOfPriceListPrice != null) {
                    oldPriceTypeOfPriceListPrice.getPriceList().remove(priceListPrice);
                    oldPriceTypeOfPriceListPrice = em.merge(oldPriceTypeOfPriceListPrice);
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListPriceTypeTranslation : priceType.getPriceTypeTranslationList()) {
                PriceType oldReferenceOfPriceTypeTranslationListPriceTypeTranslation = priceTypeTranslationListPriceTypeTranslation.getReference();
                priceTypeTranslationListPriceTypeTranslation.setReference(priceType);
                priceTypeTranslationListPriceTypeTranslation = em.merge(priceTypeTranslationListPriceTypeTranslation);
                if (oldReferenceOfPriceTypeTranslationListPriceTypeTranslation != null) {
                    oldReferenceOfPriceTypeTranslationListPriceTypeTranslation.getPriceTypeTranslationList().remove(priceTypeTranslationListPriceTypeTranslation);
                    oldReferenceOfPriceTypeTranslationListPriceTypeTranslation = em.merge(oldReferenceOfPriceTypeTranslationListPriceTypeTranslation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PriceType priceType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PriceType persistentPriceType = em.find(PriceType.class, priceType.getId());
            Employee createdUserOld = persistentPriceType.getCreatedUser();
            Employee createdUserNew = priceType.getCreatedUser();
            Employee lastModifiedUserOld = persistentPriceType.getLastModifiedUser();
            Employee lastModifiedUserNew = priceType.getLastModifiedUser();
            List<Price> priceListOld = persistentPriceType.getPriceList();
            List<Price> priceListNew = priceType.getPriceList();
            List<PriceTypeTranslation> priceTypeTranslationListOld = persistentPriceType.getPriceTypeTranslationList();
            List<PriceTypeTranslation> priceTypeTranslationListNew = priceType.getPriceTypeTranslationList();
            List<String> illegalOrphanMessages = null;
            for (Price priceListOldPrice : priceListOld) {
                if (!priceListNew.contains(priceListOldPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Price " + priceListOldPrice + " since its priceType field is not nullable.");
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListOldPriceTypeTranslation : priceTypeTranslationListOld) {
                if (!priceTypeTranslationListNew.contains(priceTypeTranslationListOldPriceTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PriceTypeTranslation " + priceTypeTranslationListOldPriceTypeTranslation + " since its reference field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                priceType.setCreatedUser(createdUserNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                priceType.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Price> attachedPriceListNew = new ArrayList<Price>();
            for (Price priceListNewPriceToAttach : priceListNew) {
                priceListNewPriceToAttach = em.getReference(priceListNewPriceToAttach.getClass(), priceListNewPriceToAttach.getId());
                attachedPriceListNew.add(priceListNewPriceToAttach);
            }
            priceListNew = attachedPriceListNew;
            priceType.setPriceList(priceListNew);
            List<PriceTypeTranslation> attachedPriceTypeTranslationListNew = new ArrayList<PriceTypeTranslation>();
            for (PriceTypeTranslation priceTypeTranslationListNewPriceTypeTranslationToAttach : priceTypeTranslationListNew) {
                priceTypeTranslationListNewPriceTypeTranslationToAttach = em.getReference(priceTypeTranslationListNewPriceTypeTranslationToAttach.getClass(), priceTypeTranslationListNewPriceTypeTranslationToAttach.getId());
                attachedPriceTypeTranslationListNew.add(priceTypeTranslationListNewPriceTypeTranslationToAttach);
            }
            priceTypeTranslationListNew = attachedPriceTypeTranslationListNew;
            priceType.setPriceTypeTranslationList(priceTypeTranslationListNew);
            priceType = em.merge(priceType);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getPriceTypeList().remove(priceType);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getPriceTypeList().add(priceType);
                createdUserNew = em.merge(createdUserNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getPriceTypeList().remove(priceType);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getPriceTypeList().add(priceType);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (Price priceListNewPrice : priceListNew) {
                if (!priceListOld.contains(priceListNewPrice)) {
                    PriceType oldPriceTypeOfPriceListNewPrice = priceListNewPrice.getPriceType();
                    priceListNewPrice.setPriceType(priceType);
                    priceListNewPrice = em.merge(priceListNewPrice);
                    if (oldPriceTypeOfPriceListNewPrice != null && !oldPriceTypeOfPriceListNewPrice.equals(priceType)) {
                        oldPriceTypeOfPriceListNewPrice.getPriceList().remove(priceListNewPrice);
                        oldPriceTypeOfPriceListNewPrice = em.merge(oldPriceTypeOfPriceListNewPrice);
                    }
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListNewPriceTypeTranslation : priceTypeTranslationListNew) {
                if (!priceTypeTranslationListOld.contains(priceTypeTranslationListNewPriceTypeTranslation)) {
                    PriceType oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation = priceTypeTranslationListNewPriceTypeTranslation.getReference();
                    priceTypeTranslationListNewPriceTypeTranslation.setReference(priceType);
                    priceTypeTranslationListNewPriceTypeTranslation = em.merge(priceTypeTranslationListNewPriceTypeTranslation);
                    if (oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation != null && !oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation.equals(priceType)) {
                        oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation.getPriceTypeTranslationList().remove(priceTypeTranslationListNewPriceTypeTranslation);
                        oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation = em.merge(oldReferenceOfPriceTypeTranslationListNewPriceTypeTranslation);
                    }
                }
            }
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Price> priceListOrphanCheck = priceType.getPriceList();
            for (Price priceListOrphanCheckPrice : priceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PriceType (" + priceType + ") cannot be destroyed since the Price " + priceListOrphanCheckPrice + " in its priceList field has a non-nullable priceType field.");
            }
            List<PriceTypeTranslation> priceTypeTranslationListOrphanCheck = priceType.getPriceTypeTranslationList();
            for (PriceTypeTranslation priceTypeTranslationListOrphanCheckPriceTypeTranslation : priceTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PriceType (" + priceType + ") cannot be destroyed since the PriceTypeTranslation " + priceTypeTranslationListOrphanCheckPriceTypeTranslation + " in its priceTypeTranslationList field has a non-nullable reference field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = priceType.getCreatedUser();
            if (createdUser != null) {
                createdUser.getPriceTypeList().remove(priceType);
                createdUser = em.merge(createdUser);
            }
            Employee lastModifiedUser = priceType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getPriceTypeList().remove(priceType);
                lastModifiedUser = em.merge(lastModifiedUser);
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
