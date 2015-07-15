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
import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class RecordJpaController implements Serializable {

    public RecordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Record record) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalyticsTime analitycsTime = record.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime = em.getReference(analitycsTime.getClass(), analitycsTime.getId());
                record.setAnalitycsTime(analitycsTime);
            }
            Stock stock = record.getStock();
            if (stock != null) {
                stock = em.getReference(stock.getClass(), stock.getId());
                record.setStock(stock);
            }
            em.persist(record);
            if (analitycsTime != null) {
                analitycsTime.getRecordList().add(record);
                analitycsTime = em.merge(analitycsTime);
            }
            if (stock != null) {
                stock.getRecordList().add(record);
                stock = em.merge(stock);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Record record) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Record persistentRecord = em.find(Record.class, record.getId());
            AnalyticsTime analitycsTimeOld = persistentRecord.getAnalitycsTime();
            AnalyticsTime analitycsTimeNew = record.getAnalitycsTime();
            Stock stockOld = persistentRecord.getStock();
            Stock stockNew = record.getStock();
            if (analitycsTimeNew != null) {
                analitycsTimeNew = em.getReference(analitycsTimeNew.getClass(), analitycsTimeNew.getId());
                record.setAnalitycsTime(analitycsTimeNew);
            }
            if (stockNew != null) {
                stockNew = em.getReference(stockNew.getClass(), stockNew.getId());
                record.setStock(stockNew);
            }
            record = em.merge(record);
            if (analitycsTimeOld != null && !analitycsTimeOld.equals(analitycsTimeNew)) {
                analitycsTimeOld.getRecordList().remove(record);
                analitycsTimeOld = em.merge(analitycsTimeOld);
            }
            if (analitycsTimeNew != null && !analitycsTimeNew.equals(analitycsTimeOld)) {
                analitycsTimeNew.getRecordList().add(record);
                analitycsTimeNew = em.merge(analitycsTimeNew);
            }
            if (stockOld != null && !stockOld.equals(stockNew)) {
                stockOld.getRecordList().remove(record);
                stockOld = em.merge(stockOld);
            }
            if (stockNew != null && !stockNew.equals(stockOld)) {
                stockNew.getRecordList().add(record);
                stockNew = em.merge(stockNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = record.getId();
                if (findRecord(id) == null) {
                    throw new NonexistentEntityException("The record with id " + id + " no longer exists.");
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
            Record record;
            try {
                record = em.getReference(Record.class, id);
                record.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The record with id " + id + " no longer exists.", enfe);
            }
            AnalyticsTime analitycsTime = record.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime.getRecordList().remove(record);
                analitycsTime = em.merge(analitycsTime);
            }
            Stock stock = record.getStock();
            if (stock != null) {
                stock.getRecordList().remove(record);
                stock = em.merge(stock);
            }
            em.remove(record);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Record> findRecordEntities() {
        return findRecordEntities(true, -1, -1);
    }

    public List<Record> findRecordEntities(int maxResults, int firstResult) {
        return findRecordEntities(false, maxResults, firstResult);
    }

    private List<Record> findRecordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Record.class));
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

    public Record findRecord(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Record.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Record> rt = cq.from(Record.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
