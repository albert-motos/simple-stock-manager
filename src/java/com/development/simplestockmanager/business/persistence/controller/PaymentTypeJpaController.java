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
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.PaymentType;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
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
        if (paymentType.getPaymentTypeTranslationList() == null) {
            paymentType.setPaymentTypeTranslationList(new ArrayList<PaymentTypeTranslation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = paymentType.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                paymentType.setCreatedUser(createdUser);
            }
            Employee lastModifiedUser = paymentType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                paymentType.setLastModifiedUser(lastModifiedUser);
            }
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : paymentType.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            paymentType.setInvoiceList(attachedInvoiceList);
            List<PaymentTypeTranslation> attachedPaymentTypeTranslationList = new ArrayList<PaymentTypeTranslation>();
            for (PaymentTypeTranslation paymentTypeTranslationListPaymentTypeTranslationToAttach : paymentType.getPaymentTypeTranslationList()) {
                paymentTypeTranslationListPaymentTypeTranslationToAttach = em.getReference(paymentTypeTranslationListPaymentTypeTranslationToAttach.getClass(), paymentTypeTranslationListPaymentTypeTranslationToAttach.getId());
                attachedPaymentTypeTranslationList.add(paymentTypeTranslationListPaymentTypeTranslationToAttach);
            }
            paymentType.setPaymentTypeTranslationList(attachedPaymentTypeTranslationList);
            em.persist(paymentType);
            if (createdUser != null) {
                createdUser.getPaymentTypeList().add(paymentType);
                createdUser = em.merge(createdUser);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getPaymentTypeList().add(paymentType);
                lastModifiedUser = em.merge(lastModifiedUser);
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
            for (PaymentTypeTranslation paymentTypeTranslationListPaymentTypeTranslation : paymentType.getPaymentTypeTranslationList()) {
                PaymentType oldReferenceOfPaymentTypeTranslationListPaymentTypeTranslation = paymentTypeTranslationListPaymentTypeTranslation.getReference();
                paymentTypeTranslationListPaymentTypeTranslation.setReference(paymentType);
                paymentTypeTranslationListPaymentTypeTranslation = em.merge(paymentTypeTranslationListPaymentTypeTranslation);
                if (oldReferenceOfPaymentTypeTranslationListPaymentTypeTranslation != null) {
                    oldReferenceOfPaymentTypeTranslationListPaymentTypeTranslation.getPaymentTypeTranslationList().remove(paymentTypeTranslationListPaymentTypeTranslation);
                    oldReferenceOfPaymentTypeTranslationListPaymentTypeTranslation = em.merge(oldReferenceOfPaymentTypeTranslationListPaymentTypeTranslation);
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
            Employee createdUserOld = persistentPaymentType.getCreatedUser();
            Employee createdUserNew = paymentType.getCreatedUser();
            Employee lastModifiedUserOld = persistentPaymentType.getLastModifiedUser();
            Employee lastModifiedUserNew = paymentType.getLastModifiedUser();
            List<Invoice> invoiceListOld = persistentPaymentType.getInvoiceList();
            List<Invoice> invoiceListNew = paymentType.getInvoiceList();
            List<PaymentTypeTranslation> paymentTypeTranslationListOld = persistentPaymentType.getPaymentTypeTranslationList();
            List<PaymentTypeTranslation> paymentTypeTranslationListNew = paymentType.getPaymentTypeTranslationList();
            List<String> illegalOrphanMessages = null;
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its paymentType field is not nullable.");
                }
            }
            for (PaymentTypeTranslation paymentTypeTranslationListOldPaymentTypeTranslation : paymentTypeTranslationListOld) {
                if (!paymentTypeTranslationListNew.contains(paymentTypeTranslationListOldPaymentTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaymentTypeTranslation " + paymentTypeTranslationListOldPaymentTypeTranslation + " since its reference field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                paymentType.setCreatedUser(createdUserNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                paymentType.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            paymentType.setInvoiceList(invoiceListNew);
            List<PaymentTypeTranslation> attachedPaymentTypeTranslationListNew = new ArrayList<PaymentTypeTranslation>();
            for (PaymentTypeTranslation paymentTypeTranslationListNewPaymentTypeTranslationToAttach : paymentTypeTranslationListNew) {
                paymentTypeTranslationListNewPaymentTypeTranslationToAttach = em.getReference(paymentTypeTranslationListNewPaymentTypeTranslationToAttach.getClass(), paymentTypeTranslationListNewPaymentTypeTranslationToAttach.getId());
                attachedPaymentTypeTranslationListNew.add(paymentTypeTranslationListNewPaymentTypeTranslationToAttach);
            }
            paymentTypeTranslationListNew = attachedPaymentTypeTranslationListNew;
            paymentType.setPaymentTypeTranslationList(paymentTypeTranslationListNew);
            paymentType = em.merge(paymentType);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getPaymentTypeList().remove(paymentType);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getPaymentTypeList().add(paymentType);
                createdUserNew = em.merge(createdUserNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getPaymentTypeList().remove(paymentType);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getPaymentTypeList().add(paymentType);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
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
            for (PaymentTypeTranslation paymentTypeTranslationListNewPaymentTypeTranslation : paymentTypeTranslationListNew) {
                if (!paymentTypeTranslationListOld.contains(paymentTypeTranslationListNewPaymentTypeTranslation)) {
                    PaymentType oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation = paymentTypeTranslationListNewPaymentTypeTranslation.getReference();
                    paymentTypeTranslationListNewPaymentTypeTranslation.setReference(paymentType);
                    paymentTypeTranslationListNewPaymentTypeTranslation = em.merge(paymentTypeTranslationListNewPaymentTypeTranslation);
                    if (oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation != null && !oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation.equals(paymentType)) {
                        oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation.getPaymentTypeTranslationList().remove(paymentTypeTranslationListNewPaymentTypeTranslation);
                        oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation = em.merge(oldReferenceOfPaymentTypeTranslationListNewPaymentTypeTranslation);
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
            List<PaymentTypeTranslation> paymentTypeTranslationListOrphanCheck = paymentType.getPaymentTypeTranslationList();
            for (PaymentTypeTranslation paymentTypeTranslationListOrphanCheckPaymentTypeTranslation : paymentTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PaymentType (" + paymentType + ") cannot be destroyed since the PaymentTypeTranslation " + paymentTypeTranslationListOrphanCheckPaymentTypeTranslation + " in its paymentTypeTranslationList field has a non-nullable reference field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = paymentType.getCreatedUser();
            if (createdUser != null) {
                createdUser.getPaymentTypeList().remove(paymentType);
                createdUser = em.merge(createdUser);
            }
            Employee lastModifiedUser = paymentType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getPaymentTypeList().remove(paymentType);
                lastModifiedUser = em.merge(lastModifiedUser);
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
