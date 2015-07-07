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
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class PriceJpaController implements Serializable {

    public PriceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Price price) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = price.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                price.setCreatedUser(createdUser);
            }
            PriceType priceType = price.getPriceType();
            if (priceType != null) {
                priceType = em.getReference(priceType.getClass(), priceType.getId());
                price.setPriceType(priceType);
            }
            Stock stock = price.getStock();
            if (stock != null) {
                stock = em.getReference(stock.getClass(), stock.getId());
                price.setStock(stock);
            }
            Employee lastModifiedUser = price.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                price.setLastModifiedUser(lastModifiedUser);
            }
            em.persist(price);
            if (createdUser != null) {
                createdUser.getPriceList().add(price);
                createdUser = em.merge(createdUser);
            }
            if (priceType != null) {
                priceType.getPriceList().add(price);
                priceType = em.merge(priceType);
            }
            if (stock != null) {
                stock.getPriceList().add(price);
                stock = em.merge(stock);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getPriceList().add(price);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Price price) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Price persistentPrice = em.find(Price.class, price.getId());
            Employee createdUserOld = persistentPrice.getCreatedUser();
            Employee createdUserNew = price.getCreatedUser();
            PriceType priceTypeOld = persistentPrice.getPriceType();
            PriceType priceTypeNew = price.getPriceType();
            Stock stockOld = persistentPrice.getStock();
            Stock stockNew = price.getStock();
            Employee lastModifiedUserOld = persistentPrice.getLastModifiedUser();
            Employee lastModifiedUserNew = price.getLastModifiedUser();
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                price.setCreatedUser(createdUserNew);
            }
            if (priceTypeNew != null) {
                priceTypeNew = em.getReference(priceTypeNew.getClass(), priceTypeNew.getId());
                price.setPriceType(priceTypeNew);
            }
            if (stockNew != null) {
                stockNew = em.getReference(stockNew.getClass(), stockNew.getId());
                price.setStock(stockNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                price.setLastModifiedUser(lastModifiedUserNew);
            }
            price = em.merge(price);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getPriceList().remove(price);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getPriceList().add(price);
                createdUserNew = em.merge(createdUserNew);
            }
            if (priceTypeOld != null && !priceTypeOld.equals(priceTypeNew)) {
                priceTypeOld.getPriceList().remove(price);
                priceTypeOld = em.merge(priceTypeOld);
            }
            if (priceTypeNew != null && !priceTypeNew.equals(priceTypeOld)) {
                priceTypeNew.getPriceList().add(price);
                priceTypeNew = em.merge(priceTypeNew);
            }
            if (stockOld != null && !stockOld.equals(stockNew)) {
                stockOld.getPriceList().remove(price);
                stockOld = em.merge(stockOld);
            }
            if (stockNew != null && !stockNew.equals(stockOld)) {
                stockNew.getPriceList().add(price);
                stockNew = em.merge(stockNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getPriceList().remove(price);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getPriceList().add(price);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = price.getId();
                if (findPrice(id) == null) {
                    throw new NonexistentEntityException("The price with id " + id + " no longer exists.");
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
            Price price;
            try {
                price = em.getReference(Price.class, id);
                price.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The price with id " + id + " no longer exists.", enfe);
            }
            Employee createdUser = price.getCreatedUser();
            if (createdUser != null) {
                createdUser.getPriceList().remove(price);
                createdUser = em.merge(createdUser);
            }
            PriceType priceType = price.getPriceType();
            if (priceType != null) {
                priceType.getPriceList().remove(price);
                priceType = em.merge(priceType);
            }
            Stock stock = price.getStock();
            if (stock != null) {
                stock.getPriceList().remove(price);
                stock = em.merge(stock);
            }
            Employee lastModifiedUser = price.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getPriceList().remove(price);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            em.remove(price);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Price> findPriceEntities() {
        return findPriceEntities(true, -1, -1);
    }

    public List<Price> findPriceEntities(int maxResults, int firstResult) {
        return findPriceEntities(false, maxResults, firstResult);
    }

    private List<Price> findPriceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Price.class));
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

    public Price findPrice(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Price.class, id);
        } finally {
            em.close();
        }
    }

    public int getPriceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Price> rt = cq.from(Price.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
