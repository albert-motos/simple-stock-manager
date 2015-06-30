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
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class SexTypeJpaController implements Serializable {

    public SexTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SexType sexType) {
        if (sexType.getEmployeeList() == null) {
            sexType.setEmployeeList(new ArrayList<Employee>());
        }
        if (sexType.getClientList() == null) {
            sexType.setClientList(new ArrayList<Client>());
        }
        if (sexType.getSexTypeList() == null) {
            sexType.setSexTypeList(new ArrayList<SexType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageType languageType = sexType.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                sexType.setLanguageType(languageType);
            }
            SexType referencedType = sexType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                sexType.setReferencedType(referencedType);
            }
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : sexType.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            sexType.setEmployeeList(attachedEmployeeList);
            List<Client> attachedClientList = new ArrayList<Client>();
            for (Client clientListClientToAttach : sexType.getClientList()) {
                clientListClientToAttach = em.getReference(clientListClientToAttach.getClass(), clientListClientToAttach.getId());
                attachedClientList.add(clientListClientToAttach);
            }
            sexType.setClientList(attachedClientList);
            List<SexType> attachedSexTypeList = new ArrayList<SexType>();
            for (SexType sexTypeListSexTypeToAttach : sexType.getSexTypeList()) {
                sexTypeListSexTypeToAttach = em.getReference(sexTypeListSexTypeToAttach.getClass(), sexTypeListSexTypeToAttach.getId());
                attachedSexTypeList.add(sexTypeListSexTypeToAttach);
            }
            sexType.setSexTypeList(attachedSexTypeList);
            em.persist(sexType);
            if (languageType != null) {
                languageType.getSexTypeList().add(sexType);
                languageType = em.merge(languageType);
            }
            if (referencedType != null) {
                referencedType.getSexTypeList().add(sexType);
                referencedType = em.merge(referencedType);
            }
            for (Employee employeeListEmployee : sexType.getEmployeeList()) {
                SexType oldSexTypeOfEmployeeListEmployee = employeeListEmployee.getSexType();
                employeeListEmployee.setSexType(sexType);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldSexTypeOfEmployeeListEmployee != null) {
                    oldSexTypeOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldSexTypeOfEmployeeListEmployee = em.merge(oldSexTypeOfEmployeeListEmployee);
                }
            }
            for (Client clientListClient : sexType.getClientList()) {
                SexType oldSexTypeOfClientListClient = clientListClient.getSexType();
                clientListClient.setSexType(sexType);
                clientListClient = em.merge(clientListClient);
                if (oldSexTypeOfClientListClient != null) {
                    oldSexTypeOfClientListClient.getClientList().remove(clientListClient);
                    oldSexTypeOfClientListClient = em.merge(oldSexTypeOfClientListClient);
                }
            }
            for (SexType sexTypeListSexType : sexType.getSexTypeList()) {
                SexType oldReferencedTypeOfSexTypeListSexType = sexTypeListSexType.getReferencedType();
                sexTypeListSexType.setReferencedType(sexType);
                sexTypeListSexType = em.merge(sexTypeListSexType);
                if (oldReferencedTypeOfSexTypeListSexType != null) {
                    oldReferencedTypeOfSexTypeListSexType.getSexTypeList().remove(sexTypeListSexType);
                    oldReferencedTypeOfSexTypeListSexType = em.merge(oldReferencedTypeOfSexTypeListSexType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SexType sexType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SexType persistentSexType = em.find(SexType.class, sexType.getId());
            LanguageType languageTypeOld = persistentSexType.getLanguageType();
            LanguageType languageTypeNew = sexType.getLanguageType();
            SexType referencedTypeOld = persistentSexType.getReferencedType();
            SexType referencedTypeNew = sexType.getReferencedType();
            List<Employee> employeeListOld = persistentSexType.getEmployeeList();
            List<Employee> employeeListNew = sexType.getEmployeeList();
            List<Client> clientListOld = persistentSexType.getClientList();
            List<Client> clientListNew = sexType.getClientList();
            List<SexType> sexTypeListOld = persistentSexType.getSexTypeList();
            List<SexType> sexTypeListNew = sexType.getSexTypeList();
            List<String> illegalOrphanMessages = null;
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its sexType field is not nullable.");
                }
            }
            for (Client clientListOldClient : clientListOld) {
                if (!clientListNew.contains(clientListOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientListOldClient + " since its sexType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                sexType.setLanguageType(languageTypeNew);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                sexType.setReferencedType(referencedTypeNew);
            }
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            sexType.setEmployeeList(employeeListNew);
            List<Client> attachedClientListNew = new ArrayList<Client>();
            for (Client clientListNewClientToAttach : clientListNew) {
                clientListNewClientToAttach = em.getReference(clientListNewClientToAttach.getClass(), clientListNewClientToAttach.getId());
                attachedClientListNew.add(clientListNewClientToAttach);
            }
            clientListNew = attachedClientListNew;
            sexType.setClientList(clientListNew);
            List<SexType> attachedSexTypeListNew = new ArrayList<SexType>();
            for (SexType sexTypeListNewSexTypeToAttach : sexTypeListNew) {
                sexTypeListNewSexTypeToAttach = em.getReference(sexTypeListNewSexTypeToAttach.getClass(), sexTypeListNewSexTypeToAttach.getId());
                attachedSexTypeListNew.add(sexTypeListNewSexTypeToAttach);
            }
            sexTypeListNew = attachedSexTypeListNew;
            sexType.setSexTypeList(sexTypeListNew);
            sexType = em.merge(sexType);
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getSexTypeList().remove(sexType);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getSexTypeList().add(sexType);
                languageTypeNew = em.merge(languageTypeNew);
            }
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getSexTypeList().remove(sexType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getSexTypeList().add(sexType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    SexType oldSexTypeOfEmployeeListNewEmployee = employeeListNewEmployee.getSexType();
                    employeeListNewEmployee.setSexType(sexType);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldSexTypeOfEmployeeListNewEmployee != null && !oldSexTypeOfEmployeeListNewEmployee.equals(sexType)) {
                        oldSexTypeOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldSexTypeOfEmployeeListNewEmployee = em.merge(oldSexTypeOfEmployeeListNewEmployee);
                    }
                }
            }
            for (Client clientListNewClient : clientListNew) {
                if (!clientListOld.contains(clientListNewClient)) {
                    SexType oldSexTypeOfClientListNewClient = clientListNewClient.getSexType();
                    clientListNewClient.setSexType(sexType);
                    clientListNewClient = em.merge(clientListNewClient);
                    if (oldSexTypeOfClientListNewClient != null && !oldSexTypeOfClientListNewClient.equals(sexType)) {
                        oldSexTypeOfClientListNewClient.getClientList().remove(clientListNewClient);
                        oldSexTypeOfClientListNewClient = em.merge(oldSexTypeOfClientListNewClient);
                    }
                }
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    sexTypeListOldSexType.setReferencedType(null);
                    sexTypeListOldSexType = em.merge(sexTypeListOldSexType);
                }
            }
            for (SexType sexTypeListNewSexType : sexTypeListNew) {
                if (!sexTypeListOld.contains(sexTypeListNewSexType)) {
                    SexType oldReferencedTypeOfSexTypeListNewSexType = sexTypeListNewSexType.getReferencedType();
                    sexTypeListNewSexType.setReferencedType(sexType);
                    sexTypeListNewSexType = em.merge(sexTypeListNewSexType);
                    if (oldReferencedTypeOfSexTypeListNewSexType != null && !oldReferencedTypeOfSexTypeListNewSexType.equals(sexType)) {
                        oldReferencedTypeOfSexTypeListNewSexType.getSexTypeList().remove(sexTypeListNewSexType);
                        oldReferencedTypeOfSexTypeListNewSexType = em.merge(oldReferencedTypeOfSexTypeListNewSexType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sexType.getId();
                if (findSexType(id) == null) {
                    throw new NonexistentEntityException("The sexType with id " + id + " no longer exists.");
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
            SexType sexType;
            try {
                sexType = em.getReference(SexType.class, id);
                sexType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Employee> employeeListOrphanCheck = sexType.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SexType (" + sexType + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable sexType field.");
            }
            List<Client> clientListOrphanCheck = sexType.getClientList();
            for (Client clientListOrphanCheckClient : clientListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SexType (" + sexType + ") cannot be destroyed since the Client " + clientListOrphanCheckClient + " in its clientList field has a non-nullable sexType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            LanguageType languageType = sexType.getLanguageType();
            if (languageType != null) {
                languageType.getSexTypeList().remove(sexType);
                languageType = em.merge(languageType);
            }
            SexType referencedType = sexType.getReferencedType();
            if (referencedType != null) {
                referencedType.getSexTypeList().remove(sexType);
                referencedType = em.merge(referencedType);
            }
            List<SexType> sexTypeList = sexType.getSexTypeList();
            for (SexType sexTypeListSexType : sexTypeList) {
                sexTypeListSexType.setReferencedType(null);
                sexTypeListSexType = em.merge(sexTypeListSexType);
            }
            em.remove(sexType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SexType> findSexTypeEntities() {
        return findSexTypeEntities(true, -1, -1);
    }

    public List<SexType> findSexTypeEntities(int maxResults, int firstResult) {
        return findSexTypeEntities(false, maxResults, firstResult);
    }

    private List<SexType> findSexTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SexType.class));
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

    public SexType findSexType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SexType.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SexType> rt = cq.from(SexType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
