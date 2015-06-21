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
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.Store;
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
public class StoreJpaController implements Serializable {

    public StoreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Store store) {
        if (store.getStockList() == null) {
            store.setStockList(new ArrayList<Stock>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee employee = store.getEmployee();
            if (employee != null) {
                employee = em.getReference(employee.getClass(), employee.getId());
                store.setEmployee(employee);
            }
            List<Stock> attachedStockList = new ArrayList<Stock>();
            for (Stock stockListStockToAttach : store.getStockList()) {
                stockListStockToAttach = em.getReference(stockListStockToAttach.getClass(), stockListStockToAttach.getId());
                attachedStockList.add(stockListStockToAttach);
            }
            store.setStockList(attachedStockList);
            em.persist(store);
            if (employee != null) {
                employee.getStoreList().add(store);
                employee = em.merge(employee);
            }
            for (Stock stockListStock : store.getStockList()) {
                Store oldStoreOfStockListStock = stockListStock.getStore();
                stockListStock.setStore(store);
                stockListStock = em.merge(stockListStock);
                if (oldStoreOfStockListStock != null) {
                    oldStoreOfStockListStock.getStockList().remove(stockListStock);
                    oldStoreOfStockListStock = em.merge(oldStoreOfStockListStock);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Store store) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Store persistentStore = em.find(Store.class, store.getId());
            Employee employeeOld = persistentStore.getEmployee();
            Employee employeeNew = store.getEmployee();
            List<Stock> stockListOld = persistentStore.getStockList();
            List<Stock> stockListNew = store.getStockList();
            List<String> illegalOrphanMessages = null;
            for (Stock stockListOldStock : stockListOld) {
                if (!stockListNew.contains(stockListOldStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stock " + stockListOldStock + " since its store field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (employeeNew != null) {
                employeeNew = em.getReference(employeeNew.getClass(), employeeNew.getId());
                store.setEmployee(employeeNew);
            }
            List<Stock> attachedStockListNew = new ArrayList<Stock>();
            for (Stock stockListNewStockToAttach : stockListNew) {
                stockListNewStockToAttach = em.getReference(stockListNewStockToAttach.getClass(), stockListNewStockToAttach.getId());
                attachedStockListNew.add(stockListNewStockToAttach);
            }
            stockListNew = attachedStockListNew;
            store.setStockList(stockListNew);
            store = em.merge(store);
            if (employeeOld != null && !employeeOld.equals(employeeNew)) {
                employeeOld.getStoreList().remove(store);
                employeeOld = em.merge(employeeOld);
            }
            if (employeeNew != null && !employeeNew.equals(employeeOld)) {
                employeeNew.getStoreList().add(store);
                employeeNew = em.merge(employeeNew);
            }
            for (Stock stockListNewStock : stockListNew) {
                if (!stockListOld.contains(stockListNewStock)) {
                    Store oldStoreOfStockListNewStock = stockListNewStock.getStore();
                    stockListNewStock.setStore(store);
                    stockListNewStock = em.merge(stockListNewStock);
                    if (oldStoreOfStockListNewStock != null && !oldStoreOfStockListNewStock.equals(store)) {
                        oldStoreOfStockListNewStock.getStockList().remove(stockListNewStock);
                        oldStoreOfStockListNewStock = em.merge(oldStoreOfStockListNewStock);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = store.getId();
                if (findStore(id) == null) {
                    throw new NonexistentEntityException("The store with id " + id + " no longer exists.");
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
            Store store;
            try {
                store = em.getReference(Store.class, id);
                store.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The store with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Stock> stockListOrphanCheck = store.getStockList();
            for (Stock stockListOrphanCheckStock : stockListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Store (" + store + ") cannot be destroyed since the Stock " + stockListOrphanCheckStock + " in its stockList field has a non-nullable store field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee employee = store.getEmployee();
            if (employee != null) {
                employee.getStoreList().remove(store);
                employee = em.merge(employee);
            }
            em.remove(store);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Store> findStoreEntities() {
        return findStoreEntities(true, -1, -1);
    }

    public List<Store> findStoreEntities(int maxResults, int firstResult) {
        return findStoreEntities(false, maxResults, firstResult);
    }

    private List<Store> findStoreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Store.class));
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

    public Store findStore(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Store.class, id);
        } finally {
            em.close();
        }
    }

    public int getStoreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Store> rt = cq.from(Store.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
