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

    public void create(EmployeeType employeeType) {
        if (employeeType.getEmployeeList() == null) {
            employeeType.setEmployeeList(new ArrayList<Employee>());
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
            em.persist(employeeType);
            if (createdUser != null) {
                createdUser.getEmployeeTypeList().add(employeeType);
                createdUser = em.merge(createdUser);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeTypeList().add(employeeType);
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
            Employee lastModifiedUserOld = persistentEmployeeType.getLastModifiedUser();
            Employee lastModifiedUserNew = employeeType.getLastModifiedUser();
            List<Employee> employeeListOld = persistentEmployeeType.getEmployeeList();
            List<Employee> employeeListNew = employeeType.getEmployeeList();
            List<String> illegalOrphanMessages = null;
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
            employeeType = em.merge(employeeType);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getEmployeeTypeList().remove(employeeType);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getEmployeeTypeList().add(employeeType);
                createdUserNew = em.merge(createdUserNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getEmployeeTypeList().remove(employeeType);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getEmployeeTypeList().add(employeeType);
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
            Employee createdUser = employeeType.getCreatedUser();
            if (createdUser != null) {
                createdUser.getEmployeeTypeList().remove(employeeType);
                createdUser = em.merge(createdUser);
            }
            Employee lastModifiedUser = employeeType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeTypeList().remove(employeeType);
                lastModifiedUser = em.merge(lastModifiedUser);
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
