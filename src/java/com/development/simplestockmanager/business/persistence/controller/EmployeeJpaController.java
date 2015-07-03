/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence.controller;

import com.development.simplestockmanager.business.persistence.Employee;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.Record;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Invoice;
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) {
        if (employee.getRecordList() == null) {
            employee.setRecordList(new ArrayList<Record>());
        }
        if (employee.getInvoiceList() == null) {
            employee.setInvoiceList(new ArrayList<Invoice>());
        }
        if (employee.getStoreList() == null) {
            employee.setStoreList(new ArrayList<Store>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmployeeType employeeType = employee.getEmployeeType();
            if (employeeType != null) {
                employeeType = em.getReference(employeeType.getClass(), employeeType.getId());
                employee.setEmployeeType(employeeType);
            }
            LanguageType languageType = employee.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                employee.setLanguageType(languageType);
            }
            List<Record> attachedRecordList = new ArrayList<Record>();
            for (Record recordListRecordToAttach : employee.getRecordList()) {
                recordListRecordToAttach = em.getReference(recordListRecordToAttach.getClass(), recordListRecordToAttach.getId());
                attachedRecordList.add(recordListRecordToAttach);
            }
            employee.setRecordList(attachedRecordList);
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : employee.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getId());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            employee.setInvoiceList(attachedInvoiceList);
            List<Store> attachedStoreList = new ArrayList<Store>();
            for (Store storeListStoreToAttach : employee.getStoreList()) {
                storeListStoreToAttach = em.getReference(storeListStoreToAttach.getClass(), storeListStoreToAttach.getId());
                attachedStoreList.add(storeListStoreToAttach);
            }
            employee.setStoreList(attachedStoreList);
            em.persist(employee);
            if (employeeType != null) {
                employeeType.getEmployeeList().add(employee);
                employeeType = em.merge(employeeType);
            }
            if (languageType != null) {
                languageType.getEmployeeList().add(employee);
                languageType = em.merge(languageType);
            }
            for (Record recordListRecord : employee.getRecordList()) {
                Employee oldEmployeeOfRecordListRecord = recordListRecord.getEmployee();
                recordListRecord.setEmployee(employee);
                recordListRecord = em.merge(recordListRecord);
                if (oldEmployeeOfRecordListRecord != null) {
                    oldEmployeeOfRecordListRecord.getRecordList().remove(recordListRecord);
                    oldEmployeeOfRecordListRecord = em.merge(oldEmployeeOfRecordListRecord);
                }
            }
            for (Invoice invoiceListInvoice : employee.getInvoiceList()) {
                Employee oldEmployeeOfInvoiceListInvoice = invoiceListInvoice.getEmployee();
                invoiceListInvoice.setEmployee(employee);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldEmployeeOfInvoiceListInvoice != null) {
                    oldEmployeeOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldEmployeeOfInvoiceListInvoice = em.merge(oldEmployeeOfInvoiceListInvoice);
                }
            }
            for (Store storeListStore : employee.getStoreList()) {
                Employee oldEmployeeOfStoreListStore = storeListStore.getEmployee();
                storeListStore.setEmployee(employee);
                storeListStore = em.merge(storeListStore);
                if (oldEmployeeOfStoreListStore != null) {
                    oldEmployeeOfStoreListStore.getStoreList().remove(storeListStore);
                    oldEmployeeOfStoreListStore = em.merge(oldEmployeeOfStoreListStore);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee persistentEmployee = em.find(Employee.class, employee.getId());
            EmployeeType employeeTypeOld = persistentEmployee.getEmployeeType();
            EmployeeType employeeTypeNew = employee.getEmployeeType();
            LanguageType languageTypeOld = persistentEmployee.getLanguageType();
            LanguageType languageTypeNew = employee.getLanguageType();
            List<Record> recordListOld = persistentEmployee.getRecordList();
            List<Record> recordListNew = employee.getRecordList();
            List<Invoice> invoiceListOld = persistentEmployee.getInvoiceList();
            List<Invoice> invoiceListNew = employee.getInvoiceList();
            List<Store> storeListOld = persistentEmployee.getStoreList();
            List<Store> storeListNew = employee.getStoreList();
            List<String> illegalOrphanMessages = null;
            for (Record recordListOldRecord : recordListOld) {
                if (!recordListNew.contains(recordListOldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordListOldRecord + " since its employee field is not nullable.");
                }
            }
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its employee field is not nullable.");
                }
            }
            for (Store storeListOldStore : storeListOld) {
                if (!storeListNew.contains(storeListOldStore)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Store " + storeListOldStore + " since its employee field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (employeeTypeNew != null) {
                employeeTypeNew = em.getReference(employeeTypeNew.getClass(), employeeTypeNew.getId());
                employee.setEmployeeType(employeeTypeNew);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                employee.setLanguageType(languageTypeNew);
            }
            List<Record> attachedRecordListNew = new ArrayList<Record>();
            for (Record recordListNewRecordToAttach : recordListNew) {
                recordListNewRecordToAttach = em.getReference(recordListNewRecordToAttach.getClass(), recordListNewRecordToAttach.getId());
                attachedRecordListNew.add(recordListNewRecordToAttach);
            }
            recordListNew = attachedRecordListNew;
            employee.setRecordList(recordListNew);
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getId());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            employee.setInvoiceList(invoiceListNew);
            List<Store> attachedStoreListNew = new ArrayList<Store>();
            for (Store storeListNewStoreToAttach : storeListNew) {
                storeListNewStoreToAttach = em.getReference(storeListNewStoreToAttach.getClass(), storeListNewStoreToAttach.getId());
                attachedStoreListNew.add(storeListNewStoreToAttach);
            }
            storeListNew = attachedStoreListNew;
            employee.setStoreList(storeListNew);
            employee = em.merge(employee);
            if (employeeTypeOld != null && !employeeTypeOld.equals(employeeTypeNew)) {
                employeeTypeOld.getEmployeeList().remove(employee);
                employeeTypeOld = em.merge(employeeTypeOld);
            }
            if (employeeTypeNew != null && !employeeTypeNew.equals(employeeTypeOld)) {
                employeeTypeNew.getEmployeeList().add(employee);
                employeeTypeNew = em.merge(employeeTypeNew);
            }
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getEmployeeList().remove(employee);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getEmployeeList().add(employee);
                languageTypeNew = em.merge(languageTypeNew);
            }
            for (Record recordListNewRecord : recordListNew) {
                if (!recordListOld.contains(recordListNewRecord)) {
                    Employee oldEmployeeOfRecordListNewRecord = recordListNewRecord.getEmployee();
                    recordListNewRecord.setEmployee(employee);
                    recordListNewRecord = em.merge(recordListNewRecord);
                    if (oldEmployeeOfRecordListNewRecord != null && !oldEmployeeOfRecordListNewRecord.equals(employee)) {
                        oldEmployeeOfRecordListNewRecord.getRecordList().remove(recordListNewRecord);
                        oldEmployeeOfRecordListNewRecord = em.merge(oldEmployeeOfRecordListNewRecord);
                    }
                }
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    Employee oldEmployeeOfInvoiceListNewInvoice = invoiceListNewInvoice.getEmployee();
                    invoiceListNewInvoice.setEmployee(employee);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldEmployeeOfInvoiceListNewInvoice != null && !oldEmployeeOfInvoiceListNewInvoice.equals(employee)) {
                        oldEmployeeOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldEmployeeOfInvoiceListNewInvoice = em.merge(oldEmployeeOfInvoiceListNewInvoice);
                    }
                }
            }
            for (Store storeListNewStore : storeListNew) {
                if (!storeListOld.contains(storeListNewStore)) {
                    Employee oldEmployeeOfStoreListNewStore = storeListNewStore.getEmployee();
                    storeListNewStore.setEmployee(employee);
                    storeListNewStore = em.merge(storeListNewStore);
                    if (oldEmployeeOfStoreListNewStore != null && !oldEmployeeOfStoreListNewStore.equals(employee)) {
                        oldEmployeeOfStoreListNewStore.getStoreList().remove(storeListNewStore);
                        oldEmployeeOfStoreListNewStore = em.merge(oldEmployeeOfStoreListNewStore);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employee.getId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
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
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Record> recordListOrphanCheck = employee.getRecordList();
            for (Record recordListOrphanCheckRecord : recordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Record " + recordListOrphanCheckRecord + " in its recordList field has a non-nullable employee field.");
            }
            List<Invoice> invoiceListOrphanCheck = employee.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable employee field.");
            }
            List<Store> storeListOrphanCheck = employee.getStoreList();
            for (Store storeListOrphanCheckStore : storeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Store " + storeListOrphanCheckStore + " in its storeList field has a non-nullable employee field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EmployeeType employeeType = employee.getEmployeeType();
            if (employeeType != null) {
                employeeType.getEmployeeList().remove(employee);
                employeeType = em.merge(employeeType);
            }
            LanguageType languageType = employee.getLanguageType();
            if (languageType != null) {
                languageType.getEmployeeList().remove(employee);
                languageType = em.merge(languageType);
            }
            em.remove(employee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
