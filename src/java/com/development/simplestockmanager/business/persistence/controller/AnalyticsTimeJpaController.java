/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.AnalyticsTime;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.Record;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class AnalyticsTimeJpaController implements Serializable {

    public AnalyticsTimeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnalyticsTime analyticsTime) {
        if (analyticsTime.getRecordList() == null) {
            analyticsTime.setRecordList(new ArrayList<Record>());
        }
        if (analyticsTime.getInvoiceList() == null) {
            analyticsTime.setInvoiceList(new ArrayList<Invoice>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Record> attachedRecordList = new ArrayList<Record>();
            for (Record recordListRecordToAttach : analyticsTime.getRecordList()) {
                recordListRecordToAttach = em.getReference(recordListRecordToAttach.getClass(), recordListRecordToAttach.getId());
                attachedRecordList.add(recordListRecordToAttach);
            }
            analyticsTime.setRecordList(attachedRecordList);
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : analyticsTime.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            analyticsTime.setInvoiceList(attachedInvoiceList);
            em.persist(analyticsTime);
            for (Record recordListRecord : analyticsTime.getRecordList()) {
                AnalyticsTime oldAnalitycsTimeOfRecordListRecord = recordListRecord.getAnalitycsTime();
                recordListRecord.setAnalitycsTime(analyticsTime);
                recordListRecord = em.merge(recordListRecord);
                if (oldAnalitycsTimeOfRecordListRecord != null) {
                    oldAnalitycsTimeOfRecordListRecord.getRecordList().remove(recordListRecord);
                    oldAnalitycsTimeOfRecordListRecord = em.merge(oldAnalitycsTimeOfRecordListRecord);
                }
            }
            for (Invoice invoiceListInvoice : analyticsTime.getInvoiceList()) {
                AnalyticsTime oldAnalitycsTimeOfInvoiceListInvoice = invoiceListInvoice.getAnalitycsTime();
                invoiceListInvoice.setAnalitycsTime(analyticsTime);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldAnalitycsTimeOfInvoiceListInvoice != null) {
                    oldAnalitycsTimeOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldAnalitycsTimeOfInvoiceListInvoice = em.merge(oldAnalitycsTimeOfInvoiceListInvoice);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnalyticsTime analyticsTime) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalyticsTime persistentAnalyticsTime = em.find(AnalyticsTime.class, analyticsTime.getId());
            List<Record> recordListOld = persistentAnalyticsTime.getRecordList();
            List<Record> recordListNew = analyticsTime.getRecordList();
            List<Invoice> invoiceListOld = persistentAnalyticsTime.getInvoiceList();
            List<Invoice> invoiceListNew = analyticsTime.getInvoiceList();
            List<String> illegalOrphanMessages = null;
            for (Record recordListOldRecord : recordListOld) {
                if (!recordListNew.contains(recordListOldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordListOldRecord + " since its analitycsTime field is not nullable.");
                }
            }
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its analitycsTime field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Record> attachedRecordListNew = new ArrayList<Record>();
            for (Record recordListNewRecordToAttach : recordListNew) {
                recordListNewRecordToAttach = em.getReference(recordListNewRecordToAttach.getClass(), recordListNewRecordToAttach.getId());
                attachedRecordListNew.add(recordListNewRecordToAttach);
            }
            recordListNew = attachedRecordListNew;
            analyticsTime.setRecordList(recordListNew);
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            analyticsTime.setInvoiceList(invoiceListNew);
            analyticsTime = em.merge(analyticsTime);
            for (Record recordListNewRecord : recordListNew) {
                if (!recordListOld.contains(recordListNewRecord)) {
                    AnalyticsTime oldAnalitycsTimeOfRecordListNewRecord = recordListNewRecord.getAnalitycsTime();
                    recordListNewRecord.setAnalitycsTime(analyticsTime);
                    recordListNewRecord = em.merge(recordListNewRecord);
                    if (oldAnalitycsTimeOfRecordListNewRecord != null && !oldAnalitycsTimeOfRecordListNewRecord.equals(analyticsTime)) {
                        oldAnalitycsTimeOfRecordListNewRecord.getRecordList().remove(recordListNewRecord);
                        oldAnalitycsTimeOfRecordListNewRecord = em.merge(oldAnalitycsTimeOfRecordListNewRecord);
                    }
                }
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    AnalyticsTime oldAnalitycsTimeOfInvoiceListNewInvoice = invoiceListNewInvoice.getAnalitycsTime();
                    invoiceListNewInvoice.setAnalitycsTime(analyticsTime);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldAnalitycsTimeOfInvoiceListNewInvoice != null && !oldAnalitycsTimeOfInvoiceListNewInvoice.equals(analyticsTime)) {
                        oldAnalitycsTimeOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldAnalitycsTimeOfInvoiceListNewInvoice = em.merge(oldAnalitycsTimeOfInvoiceListNewInvoice);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = analyticsTime.getId();
                if (findAnalyticsTime(id) == null) {
                    throw new NonexistentEntityException("The analyticsTime with id " + id + " no longer exists.");
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
            AnalyticsTime analyticsTime;
            try {
                analyticsTime = em.getReference(AnalyticsTime.class, id);
                analyticsTime.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analyticsTime with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Record> recordListOrphanCheck = analyticsTime.getRecordList();
            for (Record recordListOrphanCheckRecord : recordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AnalyticsTime (" + analyticsTime + ") cannot be destroyed since the Record " + recordListOrphanCheckRecord + " in its recordList field has a non-nullable analitycsTime field.");
            }
            List<Invoice> invoiceListOrphanCheck = analyticsTime.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AnalyticsTime (" + analyticsTime + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable analitycsTime field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(analyticsTime);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnalyticsTime> findAnalyticsTimeEntities() {
        return findAnalyticsTimeEntities(true, -1, -1);
    }

    public List<AnalyticsTime> findAnalyticsTimeEntities(int maxResults, int firstResult) {
        return findAnalyticsTimeEntities(false, maxResults, firstResult);
    }

    private List<AnalyticsTime> findAnalyticsTimeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnalyticsTime.class));
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

    public AnalyticsTime findAnalyticsTime(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnalyticsTime.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalyticsTimeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnalyticsTime> rt = cq.from(AnalyticsTime.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
