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
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.Invoice;
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
public class PaymentTypeJpaController implements Serializable {

    public PaymentTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentType paymentType) {
        if (paymentType.getInvoiceList() == null) {
            paymentType.setInvoiceList(new ArrayList<Invoice>());
        }
        if (paymentType.getPaymentTypeList() == null) {
            paymentType.setPaymentTypeList(new ArrayList<PaymentType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageType languageType = paymentType.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                paymentType.setLanguageType(languageType);
            }
            PaymentType referencedType = paymentType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                paymentType.setReferencedType(referencedType);
            }
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : paymentType.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            paymentType.setInvoiceList(attachedInvoiceList);
            List<PaymentType> attachedPaymentTypeList = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListPaymentTypeToAttach : paymentType.getPaymentTypeList()) {
                paymentTypeListPaymentTypeToAttach = em.getReference(paymentTypeListPaymentTypeToAttach.getClass(), paymentTypeListPaymentTypeToAttach.getId());
                attachedPaymentTypeList.add(paymentTypeListPaymentTypeToAttach);
            }
            paymentType.setPaymentTypeList(attachedPaymentTypeList);
            em.persist(paymentType);
            if (languageType != null) {
                languageType.getPaymentTypeList().add(paymentType);
                languageType = em.merge(languageType);
            }
            if (referencedType != null) {
                referencedType.getPaymentTypeList().add(paymentType);
                referencedType = em.merge(referencedType);
            }
            for (Invoice invoiceListInvoice : paymentType.getInvoiceList()) {
                PaymentType oldPaymentTypeOfInvoiceListInvoice = invoiceListInvoice.getPaymentType();
                invoiceListInvoice.setPaymentType(paymentType);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldPaymentTypeOfInvoiceListInvoice != null) {
                    oldPaymentTypeOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldPaymentTypeOfInvoiceListInvoice = em.merge(oldPaymentTypeOfInvoiceListInvoice);
                }
            }
            for (PaymentType paymentTypeListPaymentType : paymentType.getPaymentTypeList()) {
                PaymentType oldReferencedTypeOfPaymentTypeListPaymentType = paymentTypeListPaymentType.getReferencedType();
                paymentTypeListPaymentType.setReferencedType(paymentType);
                paymentTypeListPaymentType = em.merge(paymentTypeListPaymentType);
                if (oldReferencedTypeOfPaymentTypeListPaymentType != null) {
                    oldReferencedTypeOfPaymentTypeListPaymentType.getPaymentTypeList().remove(paymentTypeListPaymentType);
                    oldReferencedTypeOfPaymentTypeListPaymentType = em.merge(oldReferencedTypeOfPaymentTypeListPaymentType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaymentType paymentType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaymentType persistentPaymentType = em.find(PaymentType.class, paymentType.getId());
            LanguageType languageTypeOld = persistentPaymentType.getLanguageType();
            LanguageType languageTypeNew = paymentType.getLanguageType();
            PaymentType referencedTypeOld = persistentPaymentType.getReferencedType();
            PaymentType referencedTypeNew = paymentType.getReferencedType();
            List<Invoice> invoiceListOld = persistentPaymentType.getInvoiceList();
            List<Invoice> invoiceListNew = paymentType.getInvoiceList();
            List<PaymentType> paymentTypeListOld = persistentPaymentType.getPaymentTypeList();
            List<PaymentType> paymentTypeListNew = paymentType.getPaymentTypeList();
            List<String> illegalOrphanMessages = null;
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its paymentType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                paymentType.setLanguageType(languageTypeNew);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                paymentType.setReferencedType(referencedTypeNew);
            }
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            paymentType.setInvoiceList(invoiceListNew);
            List<PaymentType> attachedPaymentTypeListNew = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListNewPaymentTypeToAttach : paymentTypeListNew) {
                paymentTypeListNewPaymentTypeToAttach = em.getReference(paymentTypeListNewPaymentTypeToAttach.getClass(), paymentTypeListNewPaymentTypeToAttach.getId());
                attachedPaymentTypeListNew.add(paymentTypeListNewPaymentTypeToAttach);
            }
            paymentTypeListNew = attachedPaymentTypeListNew;
            paymentType.setPaymentTypeList(paymentTypeListNew);
            paymentType = em.merge(paymentType);
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getPaymentTypeList().remove(paymentType);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getPaymentTypeList().add(paymentType);
                languageTypeNew = em.merge(languageTypeNew);
            }
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getPaymentTypeList().remove(paymentType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getPaymentTypeList().add(paymentType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    PaymentType oldPaymentTypeOfInvoiceListNewInvoice = invoiceListNewInvoice.getPaymentType();
                    invoiceListNewInvoice.setPaymentType(paymentType);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldPaymentTypeOfInvoiceListNewInvoice != null && !oldPaymentTypeOfInvoiceListNewInvoice.equals(paymentType)) {
                        oldPaymentTypeOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldPaymentTypeOfInvoiceListNewInvoice = em.merge(oldPaymentTypeOfInvoiceListNewInvoice);
                    }
                }
            }
            for (PaymentType paymentTypeListOldPaymentType : paymentTypeListOld) {
                if (!paymentTypeListNew.contains(paymentTypeListOldPaymentType)) {
                    paymentTypeListOldPaymentType.setReferencedType(null);
                    paymentTypeListOldPaymentType = em.merge(paymentTypeListOldPaymentType);
                }
            }
            for (PaymentType paymentTypeListNewPaymentType : paymentTypeListNew) {
                if (!paymentTypeListOld.contains(paymentTypeListNewPaymentType)) {
                    PaymentType oldReferencedTypeOfPaymentTypeListNewPaymentType = paymentTypeListNewPaymentType.getReferencedType();
                    paymentTypeListNewPaymentType.setReferencedType(paymentType);
                    paymentTypeListNewPaymentType = em.merge(paymentTypeListNewPaymentType);
                    if (oldReferencedTypeOfPaymentTypeListNewPaymentType != null && !oldReferencedTypeOfPaymentTypeListNewPaymentType.equals(paymentType)) {
                        oldReferencedTypeOfPaymentTypeListNewPaymentType.getPaymentTypeList().remove(paymentTypeListNewPaymentType);
                        oldReferencedTypeOfPaymentTypeListNewPaymentType = em.merge(oldReferencedTypeOfPaymentTypeListNewPaymentType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paymentType.getId();
                if (findPaymentType(id) == null) {
                    throw new NonexistentEntityException("The paymentType with id " + id + " no longer exists.");
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
            PaymentType paymentType;
            try {
                paymentType = em.getReference(PaymentType.class, id);
                paymentType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Invoice> invoiceListOrphanCheck = paymentType.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PaymentType (" + paymentType + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable paymentType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            LanguageType languageType = paymentType.getLanguageType();
            if (languageType != null) {
                languageType.getPaymentTypeList().remove(paymentType);
                languageType = em.merge(languageType);
            }
            PaymentType referencedType = paymentType.getReferencedType();
            if (referencedType != null) {
                referencedType.getPaymentTypeList().remove(paymentType);
                referencedType = em.merge(referencedType);
            }
            List<PaymentType> paymentTypeList = paymentType.getPaymentTypeList();
            for (PaymentType paymentTypeListPaymentType : paymentTypeList) {
                paymentTypeListPaymentType.setReferencedType(null);
                paymentTypeListPaymentType = em.merge(paymentTypeListPaymentType);
            }
            em.remove(paymentType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaymentType> findPaymentTypeEntities() {
        return findPaymentTypeEntities(true, -1, -1);
    }

    public List<PaymentType> findPaymentTypeEntities(int maxResults, int firstResult) {
        return findPaymentTypeEntities(false, maxResults, firstResult);
    }

    private List<PaymentType> findPaymentTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaymentType.class));
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

    public PaymentType findPaymentType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentType.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaymentType> rt = cq.from(PaymentType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
