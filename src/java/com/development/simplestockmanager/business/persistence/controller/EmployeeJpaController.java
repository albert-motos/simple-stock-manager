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
import com.development.simplestockmanager.business.persistence.SexType;
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
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) throws IllegalOrphanException {
        if (employee.getEmployeeTypeList() == null) {
            employee.setEmployeeTypeList(new ArrayList<EmployeeType>());
        }
        if (employee.getEmployeeTypeList1() == null) {
            employee.setEmployeeTypeList1(new ArrayList<EmployeeType>());
        }
        if (employee.getEmployeeList() == null) {
            employee.setEmployeeList(new ArrayList<Employee>());
        }
        if (employee.getEmployeeList1() == null) {
            employee.setEmployeeList1(new ArrayList<Employee>());
        }
        if (employee.getSexTypeList() == null) {
            employee.setSexTypeList(new ArrayList<SexType>());
        }
        if (employee.getSexTypeList1() == null) {
            employee.setSexTypeList1(new ArrayList<SexType>());
        }
        List<String> illegalOrphanMessages = null;
        EmployeeType employeeTypeOrphanCheck = employee.getEmployeeType();
        if (employeeTypeOrphanCheck != null) {
            Employee oldCreatedUserOfEmployeeType = employeeTypeOrphanCheck.getCreatedUser();
            if (oldCreatedUserOfEmployeeType != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The EmployeeType " + employeeTypeOrphanCheck + " already has an item of type Employee whose employeeType column cannot be null. Please make another selection for the employeeType field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = employee.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                employee.setCreatedUser(createdUser);
            }
            EmployeeType employeeType = employee.getEmployeeType();
            if (employeeType != null) {
                employeeType = em.getReference(employeeType.getClass(), employeeType.getId());
                employee.setEmployeeType(employeeType);
            }
            SexType sexType = employee.getSexType();
            if (sexType != null) {
                sexType = em.getReference(sexType.getClass(), sexType.getId());
                employee.setSexType(sexType);
            }
            Employee lastModifiedUser = employee.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                employee.setLastModifiedUser(lastModifiedUser);
            }
            List<EmployeeType> attachedEmployeeTypeList = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListEmployeeTypeToAttach : employee.getEmployeeTypeList()) {
                employeeTypeListEmployeeTypeToAttach = em.getReference(employeeTypeListEmployeeTypeToAttach.getClass(), employeeTypeListEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList.add(employeeTypeListEmployeeTypeToAttach);
            }
            employee.setEmployeeTypeList(attachedEmployeeTypeList);
            List<EmployeeType> attachedEmployeeTypeList1 = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeList1EmployeeTypeToAttach : employee.getEmployeeTypeList1()) {
                employeeTypeList1EmployeeTypeToAttach = em.getReference(employeeTypeList1EmployeeTypeToAttach.getClass(), employeeTypeList1EmployeeTypeToAttach.getId());
                attachedEmployeeTypeList1.add(employeeTypeList1EmployeeTypeToAttach);
            }
            employee.setEmployeeTypeList1(attachedEmployeeTypeList1);
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : employee.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            employee.setEmployeeList(attachedEmployeeList);
            List<Employee> attachedEmployeeList1 = new ArrayList<Employee>();
            for (Employee employeeList1EmployeeToAttach : employee.getEmployeeList1()) {
                employeeList1EmployeeToAttach = em.getReference(employeeList1EmployeeToAttach.getClass(), employeeList1EmployeeToAttach.getId());
                attachedEmployeeList1.add(employeeList1EmployeeToAttach);
            }
            employee.setEmployeeList1(attachedEmployeeList1);
            List<SexType> attachedSexTypeList = new ArrayList<SexType>();
            for (SexType sexTypeListSexTypeToAttach : employee.getSexTypeList()) {
                sexTypeListSexTypeToAttach = em.getReference(sexTypeListSexTypeToAttach.getClass(), sexTypeListSexTypeToAttach.getId());
                attachedSexTypeList.add(sexTypeListSexTypeToAttach);
            }
            employee.setSexTypeList(attachedSexTypeList);
            List<SexType> attachedSexTypeList1 = new ArrayList<SexType>();
            for (SexType sexTypeList1SexTypeToAttach : employee.getSexTypeList1()) {
                sexTypeList1SexTypeToAttach = em.getReference(sexTypeList1SexTypeToAttach.getClass(), sexTypeList1SexTypeToAttach.getId());
                attachedSexTypeList1.add(sexTypeList1SexTypeToAttach);
            }
            employee.setSexTypeList1(attachedSexTypeList1);
            em.persist(employee);
            if (createdUser != null) {
                createdUser.getEmployeeList().add(employee);
                createdUser = em.merge(createdUser);
            }
            if (employeeType != null) {
                employeeType.setCreatedUser(employee);
                employeeType = em.merge(employeeType);
            }
            if (sexType != null) {
                sexType.getEmployeeList().add(employee);
                sexType = em.merge(sexType);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeList().add(employee);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (EmployeeType employeeTypeListEmployeeType : employee.getEmployeeTypeList()) {
                Employee oldCreatedUserOfEmployeeTypeListEmployeeType = employeeTypeListEmployeeType.getCreatedUser();
                employeeTypeListEmployeeType.setCreatedUser(employee);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
                if (oldCreatedUserOfEmployeeTypeListEmployeeType != null) {
                    oldCreatedUserOfEmployeeTypeListEmployeeType.getEmployeeTypeList().remove(employeeTypeListEmployeeType);
                    oldCreatedUserOfEmployeeTypeListEmployeeType = em.merge(oldCreatedUserOfEmployeeTypeListEmployeeType);
                }
            }
            for (EmployeeType employeeTypeList1EmployeeType : employee.getEmployeeTypeList1()) {
                Employee oldLastModifiedUserOfEmployeeTypeList1EmployeeType = employeeTypeList1EmployeeType.getLastModifiedUser();
                employeeTypeList1EmployeeType.setLastModifiedUser(employee);
                employeeTypeList1EmployeeType = em.merge(employeeTypeList1EmployeeType);
                if (oldLastModifiedUserOfEmployeeTypeList1EmployeeType != null) {
                    oldLastModifiedUserOfEmployeeTypeList1EmployeeType.getEmployeeTypeList1().remove(employeeTypeList1EmployeeType);
                    oldLastModifiedUserOfEmployeeTypeList1EmployeeType = em.merge(oldLastModifiedUserOfEmployeeTypeList1EmployeeType);
                }
            }
            for (Employee employeeListEmployee : employee.getEmployeeList()) {
                Employee oldCreatedUserOfEmployeeListEmployee = employeeListEmployee.getCreatedUser();
                employeeListEmployee.setCreatedUser(employee);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldCreatedUserOfEmployeeListEmployee != null) {
                    oldCreatedUserOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldCreatedUserOfEmployeeListEmployee = em.merge(oldCreatedUserOfEmployeeListEmployee);
                }
            }
            for (Employee employeeList1Employee : employee.getEmployeeList1()) {
                Employee oldLastModifiedUserOfEmployeeList1Employee = employeeList1Employee.getLastModifiedUser();
                employeeList1Employee.setLastModifiedUser(employee);
                employeeList1Employee = em.merge(employeeList1Employee);
                if (oldLastModifiedUserOfEmployeeList1Employee != null) {
                    oldLastModifiedUserOfEmployeeList1Employee.getEmployeeList1().remove(employeeList1Employee);
                    oldLastModifiedUserOfEmployeeList1Employee = em.merge(oldLastModifiedUserOfEmployeeList1Employee);
                }
            }
            for (SexType sexTypeListSexType : employee.getSexTypeList()) {
                Employee oldCreatedUserOfSexTypeListSexType = sexTypeListSexType.getCreatedUser();
                sexTypeListSexType.setCreatedUser(employee);
                sexTypeListSexType = em.merge(sexTypeListSexType);
                if (oldCreatedUserOfSexTypeListSexType != null) {
                    oldCreatedUserOfSexTypeListSexType.getSexTypeList().remove(sexTypeListSexType);
                    oldCreatedUserOfSexTypeListSexType = em.merge(oldCreatedUserOfSexTypeListSexType);
                }
            }
            for (SexType sexTypeList1SexType : employee.getSexTypeList1()) {
                Employee oldLastModifiedUserOfSexTypeList1SexType = sexTypeList1SexType.getLastModifiedUser();
                sexTypeList1SexType.setLastModifiedUser(employee);
                sexTypeList1SexType = em.merge(sexTypeList1SexType);
                if (oldLastModifiedUserOfSexTypeList1SexType != null) {
                    oldLastModifiedUserOfSexTypeList1SexType.getSexTypeList1().remove(sexTypeList1SexType);
                    oldLastModifiedUserOfSexTypeList1SexType = em.merge(oldLastModifiedUserOfSexTypeList1SexType);
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
            Employee createdUserOld = persistentEmployee.getCreatedUser();
            Employee createdUserNew = employee.getCreatedUser();
            EmployeeType employeeTypeOld = persistentEmployee.getEmployeeType();
            EmployeeType employeeTypeNew = employee.getEmployeeType();
            SexType sexTypeOld = persistentEmployee.getSexType();
            SexType sexTypeNew = employee.getSexType();
            Employee lastModifiedUserOld = persistentEmployee.getLastModifiedUser();
            Employee lastModifiedUserNew = employee.getLastModifiedUser();
            List<EmployeeType> employeeTypeListOld = persistentEmployee.getEmployeeTypeList();
            List<EmployeeType> employeeTypeListNew = employee.getEmployeeTypeList();
            List<EmployeeType> employeeTypeList1Old = persistentEmployee.getEmployeeTypeList1();
            List<EmployeeType> employeeTypeList1New = employee.getEmployeeTypeList1();
            List<Employee> employeeListOld = persistentEmployee.getEmployeeList();
            List<Employee> employeeListNew = employee.getEmployeeList();
            List<Employee> employeeList1Old = persistentEmployee.getEmployeeList1();
            List<Employee> employeeList1New = employee.getEmployeeList1();
            List<SexType> sexTypeListOld = persistentEmployee.getSexTypeList();
            List<SexType> sexTypeListNew = employee.getSexTypeList();
            List<SexType> sexTypeList1Old = persistentEmployee.getSexTypeList1();
            List<SexType> sexTypeList1New = employee.getSexTypeList1();
            List<String> illegalOrphanMessages = null;
            if (employeeTypeOld != null && !employeeTypeOld.equals(employeeTypeNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeOld + " since its createdUser field is not nullable.");
            }
            if (employeeTypeNew != null && !employeeTypeNew.equals(employeeTypeOld)) {
                Employee oldCreatedUserOfEmployeeType = employeeTypeNew.getCreatedUser();
                if (oldCreatedUserOfEmployeeType != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The EmployeeType " + employeeTypeNew + " already has an item of type Employee whose employeeType column cannot be null. Please make another selection for the employeeType field.");
                }
            }
            for (EmployeeType employeeTypeListOldEmployeeType : employeeTypeListOld) {
                if (!employeeTypeListNew.contains(employeeTypeListOldEmployeeType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeListOldEmployeeType + " since its createdUser field is not nullable.");
                }
            }
            for (EmployeeType employeeTypeList1OldEmployeeType : employeeTypeList1Old) {
                if (!employeeTypeList1New.contains(employeeTypeList1OldEmployeeType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeList1OldEmployeeType + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its createdUser field is not nullable.");
                }
            }
            for (Employee employeeList1OldEmployee : employeeList1Old) {
                if (!employeeList1New.contains(employeeList1OldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeList1OldEmployee + " since its lastModifiedUser field is not nullable.");
                }
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexType " + sexTypeListOldSexType + " since its createdUser field is not nullable.");
                }
            }
            for (SexType sexTypeList1OldSexType : sexTypeList1Old) {
                if (!sexTypeList1New.contains(sexTypeList1OldSexType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexType " + sexTypeList1OldSexType + " since its lastModifiedUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                employee.setCreatedUser(createdUserNew);
            }
            if (employeeTypeNew != null) {
                employeeTypeNew = em.getReference(employeeTypeNew.getClass(), employeeTypeNew.getId());
                employee.setEmployeeType(employeeTypeNew);
            }
            if (sexTypeNew != null) {
                sexTypeNew = em.getReference(sexTypeNew.getClass(), sexTypeNew.getId());
                employee.setSexType(sexTypeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                employee.setLastModifiedUser(lastModifiedUserNew);
            }
            List<EmployeeType> attachedEmployeeTypeListNew = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListNewEmployeeTypeToAttach : employeeTypeListNew) {
                employeeTypeListNewEmployeeTypeToAttach = em.getReference(employeeTypeListNewEmployeeTypeToAttach.getClass(), employeeTypeListNewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeListNew.add(employeeTypeListNewEmployeeTypeToAttach);
            }
            employeeTypeListNew = attachedEmployeeTypeListNew;
            employee.setEmployeeTypeList(employeeTypeListNew);
            List<EmployeeType> attachedEmployeeTypeList1New = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeList1NewEmployeeTypeToAttach : employeeTypeList1New) {
                employeeTypeList1NewEmployeeTypeToAttach = em.getReference(employeeTypeList1NewEmployeeTypeToAttach.getClass(), employeeTypeList1NewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList1New.add(employeeTypeList1NewEmployeeTypeToAttach);
            }
            employeeTypeList1New = attachedEmployeeTypeList1New;
            employee.setEmployeeTypeList1(employeeTypeList1New);
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            employee.setEmployeeList(employeeListNew);
            List<Employee> attachedEmployeeList1New = new ArrayList<Employee>();
            for (Employee employeeList1NewEmployeeToAttach : employeeList1New) {
                employeeList1NewEmployeeToAttach = em.getReference(employeeList1NewEmployeeToAttach.getClass(), employeeList1NewEmployeeToAttach.getId());
                attachedEmployeeList1New.add(employeeList1NewEmployeeToAttach);
            }
            employeeList1New = attachedEmployeeList1New;
            employee.setEmployeeList1(employeeList1New);
            List<SexType> attachedSexTypeListNew = new ArrayList<SexType>();
            for (SexType sexTypeListNewSexTypeToAttach : sexTypeListNew) {
                sexTypeListNewSexTypeToAttach = em.getReference(sexTypeListNewSexTypeToAttach.getClass(), sexTypeListNewSexTypeToAttach.getId());
                attachedSexTypeListNew.add(sexTypeListNewSexTypeToAttach);
            }
            sexTypeListNew = attachedSexTypeListNew;
            employee.setSexTypeList(sexTypeListNew);
            List<SexType> attachedSexTypeList1New = new ArrayList<SexType>();
            for (SexType sexTypeList1NewSexTypeToAttach : sexTypeList1New) {
                sexTypeList1NewSexTypeToAttach = em.getReference(sexTypeList1NewSexTypeToAttach.getClass(), sexTypeList1NewSexTypeToAttach.getId());
                attachedSexTypeList1New.add(sexTypeList1NewSexTypeToAttach);
            }
            sexTypeList1New = attachedSexTypeList1New;
            employee.setSexTypeList1(sexTypeList1New);
            employee = em.merge(employee);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getEmployeeList().remove(employee);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getEmployeeList().add(employee);
                createdUserNew = em.merge(createdUserNew);
            }
            if (employeeTypeNew != null && !employeeTypeNew.equals(employeeTypeOld)) {
                employeeTypeNew.setCreatedUser(employee);
                employeeTypeNew = em.merge(employeeTypeNew);
            }
            if (sexTypeOld != null && !sexTypeOld.equals(sexTypeNew)) {
                sexTypeOld.getEmployeeList().remove(employee);
                sexTypeOld = em.merge(sexTypeOld);
            }
            if (sexTypeNew != null && !sexTypeNew.equals(sexTypeOld)) {
                sexTypeNew.getEmployeeList().add(employee);
                sexTypeNew = em.merge(sexTypeNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getEmployeeList().remove(employee);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getEmployeeList().add(employee);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (EmployeeType employeeTypeListNewEmployeeType : employeeTypeListNew) {
                if (!employeeTypeListOld.contains(employeeTypeListNewEmployeeType)) {
                    Employee oldCreatedUserOfEmployeeTypeListNewEmployeeType = employeeTypeListNewEmployeeType.getCreatedUser();
                    employeeTypeListNewEmployeeType.setCreatedUser(employee);
                    employeeTypeListNewEmployeeType = em.merge(employeeTypeListNewEmployeeType);
                    if (oldCreatedUserOfEmployeeTypeListNewEmployeeType != null && !oldCreatedUserOfEmployeeTypeListNewEmployeeType.equals(employee)) {
                        oldCreatedUserOfEmployeeTypeListNewEmployeeType.getEmployeeTypeList().remove(employeeTypeListNewEmployeeType);
                        oldCreatedUserOfEmployeeTypeListNewEmployeeType = em.merge(oldCreatedUserOfEmployeeTypeListNewEmployeeType);
                    }
                }
            }
            for (EmployeeType employeeTypeList1NewEmployeeType : employeeTypeList1New) {
                if (!employeeTypeList1Old.contains(employeeTypeList1NewEmployeeType)) {
                    Employee oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType = employeeTypeList1NewEmployeeType.getLastModifiedUser();
                    employeeTypeList1NewEmployeeType.setLastModifiedUser(employee);
                    employeeTypeList1NewEmployeeType = em.merge(employeeTypeList1NewEmployeeType);
                    if (oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType != null && !oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType.equals(employee)) {
                        oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType.getEmployeeTypeList1().remove(employeeTypeList1NewEmployeeType);
                        oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType = em.merge(oldLastModifiedUserOfEmployeeTypeList1NewEmployeeType);
                    }
                }
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    Employee oldCreatedUserOfEmployeeListNewEmployee = employeeListNewEmployee.getCreatedUser();
                    employeeListNewEmployee.setCreatedUser(employee);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldCreatedUserOfEmployeeListNewEmployee != null && !oldCreatedUserOfEmployeeListNewEmployee.equals(employee)) {
                        oldCreatedUserOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldCreatedUserOfEmployeeListNewEmployee = em.merge(oldCreatedUserOfEmployeeListNewEmployee);
                    }
                }
            }
            for (Employee employeeList1NewEmployee : employeeList1New) {
                if (!employeeList1Old.contains(employeeList1NewEmployee)) {
                    Employee oldLastModifiedUserOfEmployeeList1NewEmployee = employeeList1NewEmployee.getLastModifiedUser();
                    employeeList1NewEmployee.setLastModifiedUser(employee);
                    employeeList1NewEmployee = em.merge(employeeList1NewEmployee);
                    if (oldLastModifiedUserOfEmployeeList1NewEmployee != null && !oldLastModifiedUserOfEmployeeList1NewEmployee.equals(employee)) {
                        oldLastModifiedUserOfEmployeeList1NewEmployee.getEmployeeList1().remove(employeeList1NewEmployee);
                        oldLastModifiedUserOfEmployeeList1NewEmployee = em.merge(oldLastModifiedUserOfEmployeeList1NewEmployee);
                    }
                }
            }
            for (SexType sexTypeListNewSexType : sexTypeListNew) {
                if (!sexTypeListOld.contains(sexTypeListNewSexType)) {
                    Employee oldCreatedUserOfSexTypeListNewSexType = sexTypeListNewSexType.getCreatedUser();
                    sexTypeListNewSexType.setCreatedUser(employee);
                    sexTypeListNewSexType = em.merge(sexTypeListNewSexType);
                    if (oldCreatedUserOfSexTypeListNewSexType != null && !oldCreatedUserOfSexTypeListNewSexType.equals(employee)) {
                        oldCreatedUserOfSexTypeListNewSexType.getSexTypeList().remove(sexTypeListNewSexType);
                        oldCreatedUserOfSexTypeListNewSexType = em.merge(oldCreatedUserOfSexTypeListNewSexType);
                    }
                }
            }
            for (SexType sexTypeList1NewSexType : sexTypeList1New) {
                if (!sexTypeList1Old.contains(sexTypeList1NewSexType)) {
                    Employee oldLastModifiedUserOfSexTypeList1NewSexType = sexTypeList1NewSexType.getLastModifiedUser();
                    sexTypeList1NewSexType.setLastModifiedUser(employee);
                    sexTypeList1NewSexType = em.merge(sexTypeList1NewSexType);
                    if (oldLastModifiedUserOfSexTypeList1NewSexType != null && !oldLastModifiedUserOfSexTypeList1NewSexType.equals(employee)) {
                        oldLastModifiedUserOfSexTypeList1NewSexType.getSexTypeList1().remove(sexTypeList1NewSexType);
                        oldLastModifiedUserOfSexTypeList1NewSexType = em.merge(oldLastModifiedUserOfSexTypeList1NewSexType);
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
            EmployeeType employeeTypeOrphanCheck = employee.getEmployeeType();
            if (employeeTypeOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeType " + employeeTypeOrphanCheck + " in its employeeType field has a non-nullable createdUser field.");
            }
            List<EmployeeType> employeeTypeListOrphanCheck = employee.getEmployeeTypeList();
            for (EmployeeType employeeTypeListOrphanCheckEmployeeType : employeeTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeType " + employeeTypeListOrphanCheckEmployeeType + " in its employeeTypeList field has a non-nullable createdUser field.");
            }
            List<EmployeeType> employeeTypeList1OrphanCheck = employee.getEmployeeTypeList1();
            for (EmployeeType employeeTypeList1OrphanCheckEmployeeType : employeeTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeType " + employeeTypeList1OrphanCheckEmployeeType + " in its employeeTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<Employee> employeeListOrphanCheck = employee.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable createdUser field.");
            }
            List<Employee> employeeList1OrphanCheck = employee.getEmployeeList1();
            for (Employee employeeList1OrphanCheckEmployee : employeeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the Employee " + employeeList1OrphanCheckEmployee + " in its employeeList1 field has a non-nullable lastModifiedUser field.");
            }
            List<SexType> sexTypeListOrphanCheck = employee.getSexTypeList();
            for (SexType sexTypeListOrphanCheckSexType : sexTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the SexType " + sexTypeListOrphanCheckSexType + " in its sexTypeList field has a non-nullable createdUser field.");
            }
            List<SexType> sexTypeList1OrphanCheck = employee.getSexTypeList1();
            for (SexType sexTypeList1OrphanCheckSexType : sexTypeList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the SexType " + sexTypeList1OrphanCheckSexType + " in its sexTypeList1 field has a non-nullable lastModifiedUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = employee.getCreatedUser();
            if (createdUser != null) {
                createdUser.getEmployeeList().remove(employee);
                createdUser = em.merge(createdUser);
            }
            SexType sexType = employee.getSexType();
            if (sexType != null) {
                sexType.getEmployeeList().remove(employee);
                sexType = em.merge(sexType);
            }
            Employee lastModifiedUser = employee.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getEmployeeList().remove(employee);
                lastModifiedUser = em.merge(lastModifiedUser);
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
