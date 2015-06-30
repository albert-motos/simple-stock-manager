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
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.Item;
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
public class InvoiceJpaController implements Serializable {

    public InvoiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Invoice invoice) {
        if (invoice.getItemList() == null) {
            invoice.setItemList(new ArrayList<Item>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalyticsTime analitycsTime = invoice.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime = em.getReference(analitycsTime.getClass(), analitycsTime.getId());
                invoice.setAnalitycsTime(analitycsTime);
            }
            Client client = invoice.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getId());
                invoice.setClient(client);
            }
            Employee employee = invoice.getEmployee();
            if (employee != null) {
                employee = em.getReference(employee.getClass(), employee.getId());
                invoice.setEmployee(employee);
            }
            PaymentType paymentType = invoice.getPaymentType();
            if (paymentType != null) {
                paymentType = em.getReference(paymentType.getClass(), paymentType.getId());
                invoice.setPaymentType(paymentType);
            }
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : invoice.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getId());
                attachedItemList.add(itemListItemToAttach);
            }
            invoice.setItemList(attachedItemList);
            em.persist(invoice);
            if (analitycsTime != null) {
                analitycsTime.getInvoiceList().add(invoice);
                analitycsTime = em.merge(analitycsTime);
            }
            if (client != null) {
                client.getInvoiceList().add(invoice);
                client = em.merge(client);
            }
            if (employee != null) {
                employee.getInvoiceList().add(invoice);
                employee = em.merge(employee);
            }
            if (paymentType != null) {
                paymentType.getInvoiceList().add(invoice);
                paymentType = em.merge(paymentType);
            }
            for (Item itemListItem : invoice.getItemList()) {
                Invoice oldInvoiceOfItemListItem = itemListItem.getInvoice();
                itemListItem.setInvoice(invoice);
                itemListItem = em.merge(itemListItem);
                if (oldInvoiceOfItemListItem != null) {
                    oldInvoiceOfItemListItem.getItemList().remove(itemListItem);
                    oldInvoiceOfItemListItem = em.merge(oldInvoiceOfItemListItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Invoice invoice) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice persistentInvoice = em.find(Invoice.class, invoice.getId());
            AnalyticsTime analitycsTimeOld = persistentInvoice.getAnalitycsTime();
            AnalyticsTime analitycsTimeNew = invoice.getAnalitycsTime();
            Client clientOld = persistentInvoice.getClient();
            Client clientNew = invoice.getClient();
            Employee employeeOld = persistentInvoice.getEmployee();
            Employee employeeNew = invoice.getEmployee();
            PaymentType paymentTypeOld = persistentInvoice.getPaymentType();
            PaymentType paymentTypeNew = invoice.getPaymentType();
            List<Item> itemListOld = persistentInvoice.getItemList();
            List<Item> itemListNew = invoice.getItemList();
            List<String> illegalOrphanMessages = null;
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its invoice field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (analitycsTimeNew != null) {
                analitycsTimeNew = em.getReference(analitycsTimeNew.getClass(), analitycsTimeNew.getId());
                invoice.setAnalitycsTime(analitycsTimeNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getId());
                invoice.setClient(clientNew);
            }
            if (employeeNew != null) {
                employeeNew = em.getReference(employeeNew.getClass(), employeeNew.getId());
                invoice.setEmployee(employeeNew);
            }
            if (paymentTypeNew != null) {
                paymentTypeNew = em.getReference(paymentTypeNew.getClass(), paymentTypeNew.getId());
                invoice.setPaymentType(paymentTypeNew);
            }
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getId());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            invoice.setItemList(itemListNew);
            invoice = em.merge(invoice);
            if (analitycsTimeOld != null && !analitycsTimeOld.equals(analitycsTimeNew)) {
                analitycsTimeOld.getInvoiceList().remove(invoice);
                analitycsTimeOld = em.merge(analitycsTimeOld);
            }
            if (analitycsTimeNew != null && !analitycsTimeNew.equals(analitycsTimeOld)) {
                analitycsTimeNew.getInvoiceList().add(invoice);
                analitycsTimeNew = em.merge(analitycsTimeNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getInvoiceList().remove(invoice);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getInvoiceList().add(invoice);
                clientNew = em.merge(clientNew);
            }
            if (employeeOld != null && !employeeOld.equals(employeeNew)) {
                employeeOld.getInvoiceList().remove(invoice);
                employeeOld = em.merge(employeeOld);
            }
            if (employeeNew != null && !employeeNew.equals(employeeOld)) {
                employeeNew.getInvoiceList().add(invoice);
                employeeNew = em.merge(employeeNew);
            }
            if (paymentTypeOld != null && !paymentTypeOld.equals(paymentTypeNew)) {
                paymentTypeOld.getInvoiceList().remove(invoice);
                paymentTypeOld = em.merge(paymentTypeOld);
            }
            if (paymentTypeNew != null && !paymentTypeNew.equals(paymentTypeOld)) {
                paymentTypeNew.getInvoiceList().add(invoice);
                paymentTypeNew = em.merge(paymentTypeNew);
            }
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    Invoice oldInvoiceOfItemListNewItem = itemListNewItem.getInvoice();
                    itemListNewItem.setInvoice(invoice);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldInvoiceOfItemListNewItem != null && !oldInvoiceOfItemListNewItem.equals(invoice)) {
                        oldInvoiceOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldInvoiceOfItemListNewItem = em.merge(oldInvoiceOfItemListNewItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = invoice.getId();
                if (findInvoice(id) == null) {
                    throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.");
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
            Invoice invoice;
            try {
                invoice = em.getReference(Invoice.class, id);
                invoice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Item> itemListOrphanCheck = invoice.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable invoice field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AnalyticsTime analitycsTime = invoice.getAnalitycsTime();
            if (analitycsTime != null) {
                analitycsTime.getInvoiceList().remove(invoice);
                analitycsTime = em.merge(analitycsTime);
            }
            Client client = invoice.getClient();
            if (client != null) {
                client.getInvoiceList().remove(invoice);
                client = em.merge(client);
            }
            Employee employee = invoice.getEmployee();
            if (employee != null) {
                employee.getInvoiceList().remove(invoice);
                employee = em.merge(employee);
            }
            PaymentType paymentType = invoice.getPaymentType();
            if (paymentType != null) {
                paymentType.getInvoiceList().remove(invoice);
                paymentType = em.merge(paymentType);
            }
            em.remove(invoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Invoice> findInvoiceEntities() {
        return findInvoiceEntities(true, -1, -1);
    }

    public List<Invoice> findInvoiceEntities(int maxResults, int firstResult) {
        return findInvoiceEntities(false, maxResults, firstResult);
    }

    private List<Invoice> findInvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invoice.class));
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

    public Invoice findInvoice(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Invoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invoice> rt = cq.from(Invoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
