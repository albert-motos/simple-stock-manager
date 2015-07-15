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
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.Price;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class ItemJpaController implements Serializable {

    public ItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice invoice = item.getInvoice();
            if (invoice != null) {
                invoice = em.getReference(invoice.getClass(), invoice.getId());
                item.setInvoice(invoice);
            }
            Price price = item.getPrice();
            if (price != null) {
                price = em.getReference(price.getClass(), price.getId());
                item.setPrice(price);
            }
            Stock stock = item.getStock();
            if (stock != null) {
                stock = em.getReference(stock.getClass(), stock.getId());
                item.setStock(stock);
            }
            em.persist(item);
            if (invoice != null) {
                invoice.getItemList().add(item);
                invoice = em.merge(invoice);
            }
            if (price != null) {
                price.getItemList().add(item);
                price = em.merge(price);
            }
            if (stock != null) {
                stock.getItemList().add(item);
                stock = em.merge(stock);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Item item) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getId());
            Invoice invoiceOld = persistentItem.getInvoice();
            Invoice invoiceNew = item.getInvoice();
            Price priceOld = persistentItem.getPrice();
            Price priceNew = item.getPrice();
            Stock stockOld = persistentItem.getStock();
            Stock stockNew = item.getStock();
            if (invoiceNew != null) {
                invoiceNew = em.getReference(invoiceNew.getClass(), invoiceNew.getId());
                item.setInvoice(invoiceNew);
            }
            if (priceNew != null) {
                priceNew = em.getReference(priceNew.getClass(), priceNew.getId());
                item.setPrice(priceNew);
            }
            if (stockNew != null) {
                stockNew = em.getReference(stockNew.getClass(), stockNew.getId());
                item.setStock(stockNew);
            }
            item = em.merge(item);
            if (invoiceOld != null && !invoiceOld.equals(invoiceNew)) {
                invoiceOld.getItemList().remove(item);
                invoiceOld = em.merge(invoiceOld);
            }
            if (invoiceNew != null && !invoiceNew.equals(invoiceOld)) {
                invoiceNew.getItemList().add(item);
                invoiceNew = em.merge(invoiceNew);
            }
            if (priceOld != null && !priceOld.equals(priceNew)) {
                priceOld.getItemList().remove(item);
                priceOld = em.merge(priceOld);
            }
            if (priceNew != null && !priceNew.equals(priceOld)) {
                priceNew.getItemList().add(item);
                priceNew = em.merge(priceNew);
            }
            if (stockOld != null && !stockOld.equals(stockNew)) {
                stockOld.getItemList().remove(item);
                stockOld = em.merge(stockOld);
            }
            if (stockNew != null && !stockNew.equals(stockOld)) {
                stockNew.getItemList().add(item);
                stockNew = em.merge(stockNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = item.getId();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            Invoice invoice = item.getInvoice();
            if (invoice != null) {
                invoice.getItemList().remove(item);
                invoice = em.merge(invoice);
            }
            Price price = item.getPrice();
            if (price != null) {
                price.getItemList().remove(item);
                price = em.merge(price);
            }
            Stock stock = item.getStock();
            if (stock != null) {
                stock.getItemList().remove(item);
                stock = em.merge(stock);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
