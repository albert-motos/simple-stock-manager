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
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.LanguageType;
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
public class EmployeeTypeJpaController implements Serializable {

    public EmployeeTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeType employeeType) throws IllegalOrphanException {
        if (employeeType.getEmployeeList() == null) {
            employeeType.setEmployeeList(new ArrayList<Employee>());
        }
        if (employeeType.getEmployeeTypeList() == null) {
            employeeType.setEmployeeTypeList(new ArrayList<EmployeeType>());
        }
        List<String> illegalOrphanMessages = null;
        Employee createdUserOrphanCheck = employeeType.getCreatedUser();
        if (createdUserOrphanCheck != null) {
            EmployeeType oldEmployeeTypeOfCreatedUser = createdUserOrphanCheck.getEmployeeType();
            if (oldEmployeeTypeOfCreatedUser != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Employee " + createdUserOrphanCheck + " already has an item of type EmployeeType whose createdUser column cannot be null. Please make another selection for the createdUser field.");
            }
        }
        Employee lastModifiedUserOrphanCheck = employeeType.getLastModifiedUser();
        if (lastModifiedUserOrphanCheck != null) {
            EmployeeType oldEmployeeTypeOfLastModifiedUser = lastModifiedUserOrphanCheck.getEmployeeType();
            if (oldEmployeeTypeOfLastModifiedUser != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Employee " + lastModifiedUserOrphanCheck + " already has an item of type EmployeeType whose lastModifiedUser column cannot be null. Please make another selection for the lastModifiedUser field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = employeeType.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                employeeType.setCreatedUser(createdUser);
            }
            EmployeeType referencedType = employeeType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                employeeType.setReferencedType(referencedType);
            }
            LanguageType languageType = employeeType.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                employeeType.setLanguageType(languageType);
            }
            Employee lastModifiedUser = employeeType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                employeeType.setLastModifiedUser(lastModifiedUser);
            }
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : employeeType.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            employeeType.setEmployeeList(attachedEmployeeList);
            List<EmployeeType> attachedEmployeeTypeList = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListEmployeeTypeToAttach : employeeType.getEmployeeTypeList()) {
                employeeTypeListEmployeeTypeToAttach = em.getReference(employeeTypeListEmployeeTypeToAttach.getClass(), employeeTypeListEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList.add(employeeTypeListEmployeeTypeToAttach);
            }
            employeeType.setEmployeeTypeList(attachedEmployeeTypeList);
            em.persist(employeeType);
            if (createdUser != null) {
                createdUser.setEmployeeType(employeeType);
                createdUser = em.merge(createdUser);
            }
            if (referencedType != null) {
                referencedType.getEmployeeTypeList().add(employeeType);
                referencedType = em.merge(referencedType);
            }
            if (languageType != null) {
                languageType.getEmployeeTypeList().add(employeeType);
                languageType = em.merge(languageType);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.setEmployeeType(employeeType);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (Employee employeeListEmployee : employeeType.getEmployeeList()) {
                EmployeeType oldEmployeeTypeOfEmployeeListEmployee = employeeListEmployee.getEmployeeType();
                employeeListEmployee.setEmployeeType(employeeType);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldEmployeeTypeOfEmployeeListEmployee != null) {
                    oldEmployeeTypeOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldEmployeeTypeOfEmployeeListEmployee = em.merge(oldEmployeeTypeOfEmployeeListEmployee);
                }
            }
            for (EmployeeType employeeTypeListEmployeeType : employeeType.getEmployeeTypeList()) {
                EmployeeType oldReferencedTypeOfEmployeeTypeListEmployeeType = employeeTypeListEmployeeType.getReferencedType();
                employeeTypeListEmployeeType.setReferencedType(employeeType);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
                if (oldReferencedTypeOfEmployeeTypeListEmployeeType != null) {
                    oldReferencedTypeOfEmployeeTypeListEmployeeType.getEmployeeTypeList().remove(employeeTypeListEmployeeType);
                    oldReferencedTypeOfEmployeeTypeListEmployeeType = em.merge(oldReferencedTypeOfEmployeeTypeListEmployeeType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeType employeeType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmployeeType persistentEmployeeType = em.find(EmployeeType.class, employeeType.getId());
            Employee createdUserOld = persistentEmployeeType.getCreatedUser();
            Employee createdUserNew = employeeType.getCreatedUser();
            EmployeeType referencedTypeOld = persistentEmployeeType.getReferencedType();
            EmployeeType referencedTypeNew = employeeType.getReferencedType();
            LanguageType languageTypeOld = persistentEmployeeType.getLanguageType();
            LanguageType languageTypeNew = employeeType.getLanguageType();
            Employee lastModifiedUserOld = persistentEmployeeType.getLastModifiedUser();
            Employee lastModifiedUserNew = employeeType.getLastModifiedUser();
            List<Employee> employeeListOld = persistentEmployeeType.getEmployeeList();
            List<Employee> employeeListNew = employeeType.getEmployeeList();
            List<EmployeeType> employeeTypeListOld = persistentEmployeeType.getEmployeeTypeList();
            List<EmployeeType> employeeTypeListNew = employeeType.getEmployeeTypeList();
            List<String> illegalOrphanMessages = null;
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Employee " + createdUserOld + " since its employeeType field is not nullable.");
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                EmployeeType oldEmployeeTypeOfCreatedUser = createdUserNew.getEmployeeType();
                if (oldEmployeeTypeOfCreatedUser != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Employee " + createdUserNew + " already has an item of type EmployeeType whose createdUser column cannot be null. Please make another selection for the createdUser field.");
                }
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Employee " + lastModifiedUserOld + " since its employeeType field is not nullable.");
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                EmployeeType oldEmployeeTypeOfLastModifiedUser = lastModifiedUserNew.getEmployeeType();
                if (oldEmployeeTypeOfLastModifiedUser != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Employee " + lastModifiedUserNew + " already has an item of type EmployeeType whose lastModifiedUser column cannot be null. Please make another selection for the lastModifiedUser field.");
                }
            }
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its employeeType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                employeeType.setCreatedUser(createdUserNew);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                employeeType.setReferencedType(referencedTypeNew);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                employeeType.setLanguageType(languageTypeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                employeeType.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            employeeType.setEmployeeList(employeeListNew);
            List<EmployeeType> attachedEmployeeTypeListNew = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListNewEmployeeTypeToAttach : employeeTypeListNew) {
                employeeTypeListNewEmployeeTypeToAttach = em.getReference(employeeTypeListNewEmployeeTypeToAttach.getClass(), employeeTypeListNewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeListNew.add(employeeTypeListNewEmployeeTypeToAttach);
            }
            employeeTypeListNew = attachedEmployeeTypeListNew;
            employeeType.setEmployeeTypeList(employeeTypeListNew);
            employeeType = em.merge(employeeType);
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.setEmployeeType(employeeType);
                createdUserNew = em.merge(createdUserNew);
            }
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getEmployeeTypeList().remove(employeeType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getEmployeeTypeList().add(employeeType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getEmployeeTypeList().remove(employeeType);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getEmployeeTypeList().add(employeeType);
                languageTypeNew = em.merge(languageTypeNew);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.setEmployeeType(employeeType);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    EmployeeType oldEmployeeTypeOfEmployeeListNewEmployee = employeeListNewEmployee.getEmployeeType();
                    employeeListNewEmployee.setEmployeeType(employeeType);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldEmployeeTypeOfEmployeeListNewEmployee != null && !oldEmployeeTypeOfEmployeeListNewEmployee.equals(employeeType)) {
                        oldEmployeeTypeOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldEmployeeTypeOfEmployeeListNewEmployee = em.merge(oldEmployeeTypeOfEmployeeListNewEmployee);
                    }
                }
            }
            for (EmployeeType employeeTypeListOldEmployeeType : employeeTypeListOld) {
                if (!employeeTypeListNew.contains(employeeTypeListOldEmployeeType)) {
                    employeeTypeListOldEmployeeType.setReferencedType(null);
                    employeeTypeListOldEmployeeType = em.merge(employeeTypeListOldEmployeeType);
                }
            }
            for (EmployeeType employeeTypeListNewEmployeeType : employeeTypeListNew) {
                if (!employeeTypeListOld.contains(employeeTypeListNewEmployeeType)) {
                    EmployeeType oldReferencedTypeOfEmployeeTypeListNewEmployeeType = employeeTypeListNewEmployeeType.getReferencedType();
                    employeeTypeListNewEmployeeType.setReferencedType(employeeType);
                    employeeTypeListNewEmployeeType = em.merge(employeeTypeListNewEmployeeType);
                    if (oldReferencedTypeOfEmployeeTypeListNewEmployeeType != null && !oldReferencedTypeOfEmployeeTypeListNewEmployeeType.equals(employeeType)) {
                        oldReferencedTypeOfEmployeeTypeListNewEmployeeType.getEmployeeTypeList().remove(employeeTypeListNewEmployeeType);
                        oldReferencedTypeOfEmployeeTypeListNewEmployeeType = em.merge(oldReferencedTypeOfEmployeeTypeListNewEmployeeType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = employeeType.getId();
                if (findEmployeeType(id) == null) {
                    throw new NonexistentEntityException("The employeeType with id " + id + " no longer exists.");
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
            EmployeeType employeeType;
            try {
                employeeType = em.getReference(EmployeeType.class, id);
                employeeType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Employee createdUserOrphanCheck = employeeType.getCreatedUser();
            if (createdUserOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmployeeType (" + employeeType + ") cannot be destroyed since the Employee " + createdUserOrphanCheck + " in its createdUser field has a non-nullable employeeType field.");
            }
            Employee lastModifiedUserOrphanCheck = employeeType.getLastModifiedUser();
            if (lastModifiedUserOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmployeeType (" + employeeType + ") cannot be destroyed since the Employee " + lastModifiedUserOrphanCheck + " in its lastModifiedUser field has a non-nullable employeeType field.");
            }
            List<Employee> employeeListOrphanCheck = employeeType.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmployeeType (" + employeeType + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable employeeType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EmployeeType referencedType = employeeType.getReferencedType();
            if (referencedType != null) {
                referencedType.getEmployeeTypeList().remove(employeeType);
                referencedType = em.merge(referencedType);
            }
            LanguageType languageType = employeeType.getLanguageType();
            if (languageType != null) {
                languageType.getEmployeeTypeList().remove(employeeType);
                languageType = em.merge(languageType);
            }
            List<EmployeeType> employeeTypeList = employeeType.getEmployeeTypeList();
            for (EmployeeType employeeTypeListEmployeeType : employeeTypeList) {
                employeeTypeListEmployeeType.setReferencedType(null);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
            }
            em.remove(employeeType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmployeeType> findEmployeeTypeEntities() {
        return findEmployeeTypeEntities(true, -1, -1);
    }

    public List<EmployeeType> findEmployeeTypeEntities(int maxResults, int firstResult) {
        return findEmployeeTypeEntities(false, maxResults, firstResult);
    }

    private List<EmployeeType> findEmployeeTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeType.class));
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

    public EmployeeType findEmployeeType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeType.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeType> rt = cq.from(EmployeeType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
