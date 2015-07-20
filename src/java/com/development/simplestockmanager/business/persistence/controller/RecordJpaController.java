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
            Employee createdUser = record.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                record.setCreatedUser(createdUser);
            }
            AnalyticsTime analitycsTime = record.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime = em.getReference(analitycsTime.getClass(), analitycsTime.getId());
                record.setAnalitycsTime(analitycsTime);
            }
            Employee employee = record.getEmployee();
            if (employee != null) {
                employee = em.getReference(employee.getClass(), employee.getId());
                record.setEmployee(employee);
            }
            Stock stock = record.getStock();
            if (stock != null) {
                stock = em.getReference(stock.getClass(), stock.getId());
                record.setStock(stock);
            }
            Employee lastModifiedUser = record.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                record.setLastModifiedUser(lastModifiedUser);
            }
            em.persist(record);
            if (createdUser != null) {
                createdUser.getRecordList().add(record);
                createdUser = em.merge(createdUser);
            }
            if (analitycsTime != null) {
                analitycsTime.getRecordList().add(record);
                analitycsTime = em.merge(analitycsTime);
            }
            if (employee != null) {
                employee.getRecordList().add(record);
                employee = em.merge(employee);
            }
            if (stock != null) {
                stock.getRecordList().add(record);
                stock = em.merge(stock);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getRecordList().add(record);
                lastModifiedUser = em.merge(lastModifiedUser);
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
            Employee createdUserOld = persistentRecord.getCreatedUser();
            Employee createdUserNew = record.getCreatedUser();
            AnalyticsTime analitycsTimeOld = persistentRecord.getAnalitycsTime();
            AnalyticsTime analitycsTimeNew = record.getAnalitycsTime();
            Employee employeeOld = persistentRecord.getEmployee();
            Employee employeeNew = record.getEmployee();
            Stock stockOld = persistentRecord.getStock();
            Stock stockNew = record.getStock();
            Employee lastModifiedUserOld = persistentRecord.getLastModifiedUser();
            Employee lastModifiedUserNew = record.getLastModifiedUser();
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                record.setCreatedUser(createdUserNew);
            }
            if (analitycsTimeNew != null) {
                analitycsTimeNew = em.getReference(analitycsTimeNew.getClass(), analitycsTimeNew.getId());
                record.setAnalitycsTime(analitycsTimeNew);
            }
            if (employeeNew != null) {
                employeeNew = em.getReference(employeeNew.getClass(), employeeNew.getId());
                record.setEmployee(employeeNew);
            }
            if (stockNew != null) {
                stockNew = em.getReference(stockNew.getClass(), stockNew.getId());
                record.setStock(stockNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                record.setLastModifiedUser(lastModifiedUserNew);
            }
            record = em.merge(record);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getRecordList().remove(record);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getRecordList().add(record);
                createdUserNew = em.merge(createdUserNew);
            }
            if (analitycsTimeOld != null && !analitycsTimeOld.equals(analitycsTimeNew)) {
                analitycsTimeOld.getRecordList().remove(record);
                analitycsTimeOld = em.merge(analitycsTimeOld);
            }
            if (analitycsTimeNew != null && !analitycsTimeNew.equals(analitycsTimeOld)) {
                analitycsTimeNew.getRecordList().add(record);
                analitycsTimeNew = em.merge(analitycsTimeNew);
            }
            if (employeeOld != null && !employeeOld.equals(employeeNew)) {
                employeeOld.getRecordList().remove(record);
                employeeOld = em.merge(employeeOld);
            }
            if (employeeNew != null && !employeeNew.equals(employeeOld)) {
                employeeNew.getRecordList().add(record);
                employeeNew = em.merge(employeeNew);
            }
            if (stockOld != null && !stockOld.equals(stockNew)) {
                stockOld.getRecordList().remove(record);
                stockOld = em.merge(stockOld);
            }
            if (stockNew != null && !stockNew.equals(stockOld)) {
                stockNew.getRecordList().add(record);
                stockNew = em.merge(stockNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getRecordList().remove(record);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getRecordList().add(record);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
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
            Employee createdUser = record.getCreatedUser();
            if (createdUser != null) {
                createdUser.getRecordList().remove(record);
                createdUser = em.merge(createdUser);
            }
            AnalyticsTime analitycsTime = record.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime.getRecordList().remove(record);
                analitycsTime = em.merge(analitycsTime);
            }
            Employee employee = record.getEmployee();
            if (employee != null) {
                employee.getRecordList().remove(record);
                employee = em.merge(employee);
            }
            Stock stock = record.getStock();
            if (stock != null) {
                stock.getRecordList().remove(record);
                stock = em.merge(stock);
            }
            Employee lastModifiedUser = record.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getRecordList().remove(record);
                lastModifiedUser = em.merge(lastModifiedUser);
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
