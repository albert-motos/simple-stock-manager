/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.Client;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.SexType;
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
public class ClientJpaController implements Serializable {

    public ClientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) {
        if (client.getInvoiceList() == null) {
            client.setInvoiceList(new ArrayList<Invoice>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = client.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                client.setCreatedUser(createdUser);
            }
            SexType sexType = client.getSexType();
            if (sexType != null) {
                sexType = em.getReference(sexType.getClass(), sexType.getId());
                client.setSexType(sexType);
            }
            Employee lastModifiedUser = client.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                client.setLastModifiedUser(lastModifiedUser);
            }
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : client.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            client.setInvoiceList(attachedInvoiceList);
            em.persist(client);
            if (createdUser != null) {
                createdUser.getClientList().add(client);
                createdUser = em.merge(createdUser);
            }
            if (sexType != null) {
                sexType.getClientList().add(client);
                sexType = em.merge(sexType);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getClientList().add(client);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (Invoice invoiceListInvoice : client.getInvoiceList()) {
                Client oldClientOfInvoiceListInvoice = invoiceListInvoice.getClient();
                invoiceListInvoice.setClient(client);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldClientOfInvoiceListInvoice != null) {
                    oldClientOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldClientOfInvoiceListInvoice = em.merge(oldClientOfInvoiceListInvoice);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getId());
            Employee createdUserOld = persistentClient.getCreatedUser();
            Employee createdUserNew = client.getCreatedUser();
            SexType sexTypeOld = persistentClient.getSexType();
            SexType sexTypeNew = client.getSexType();
            Employee lastModifiedUserOld = persistentClient.getLastModifiedUser();
            Employee lastModifiedUserNew = client.getLastModifiedUser();
            List<Invoice> invoiceListOld = persistentClient.getInvoiceList();
            List<Invoice> invoiceListNew = client.getInvoiceList();
            List<String> illegalOrphanMessages = null;
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its client field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                client.setCreatedUser(createdUserNew);
            }
            if (sexTypeNew != null) {
                sexTypeNew = em.getReference(sexTypeNew.getClass(), sexTypeNew.getId());
                client.setSexType(sexTypeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                client.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            client.setInvoiceList(invoiceListNew);
            client = em.merge(client);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getClientList().remove(client);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getClientList().add(client);
                createdUserNew = em.merge(createdUserNew);
            }
            if (sexTypeOld != null && !sexTypeOld.equals(sexTypeNew)) {
                sexTypeOld.getClientList().remove(client);
                sexTypeOld = em.merge(sexTypeOld);
            }
            if (sexTypeNew != null && !sexTypeNew.equals(sexTypeOld)) {
                sexTypeNew.getClientList().add(client);
                sexTypeNew = em.merge(sexTypeNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getClientList().remove(client);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getClientList().add(client);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    Client oldClientOfInvoiceListNewInvoice = invoiceListNewInvoice.getClient();
                    invoiceListNewInvoice.setClient(client);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldClientOfInvoiceListNewInvoice != null && !oldClientOfInvoiceListNewInvoice.equals(client)) {
                        oldClientOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldClientOfInvoiceListNewInvoice = em.merge(oldClientOfInvoiceListNewInvoice);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = client.getId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Invoice> invoiceListOrphanCheck = client.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Client (" + client + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable client field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = client.getCreatedUser();
            if (createdUser != null) {
                createdUser.getClientList().remove(client);
                createdUser = em.merge(createdUser);
            }
            SexType sexType = client.getSexType();
            if (sexType != null) {
                sexType.getClientList().remove(client);
                sexType = em.merge(sexType);
            }
            Employee lastModifiedUser = client.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getClientList().remove(client);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
