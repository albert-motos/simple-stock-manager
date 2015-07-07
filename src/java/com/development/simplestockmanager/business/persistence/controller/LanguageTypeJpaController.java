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
import com.development.simplestockmanager.business.persistence.Employee;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.PriceType;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class LanguageTypeJpaController implements Serializable {

    public LanguageTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LanguageType languageType) {
        if (languageType.getEmployeeList() == null) {
            languageType.setEmployeeList(new ArrayList<Employee>());
        }
        if (languageType.getPriceTypeList() == null) {
            languageType.setPriceTypeList(new ArrayList<PriceType>());
        }
        if (languageType.getLanguageTypeList() == null) {
            languageType.setLanguageTypeList(new ArrayList<LanguageType>());
        }
        if (languageType.getSexTypeList() == null) {
            languageType.setSexTypeList(new ArrayList<SexType>());
        }
        if (languageType.getPaymentTypeList() == null) {
            languageType.setPaymentTypeList(new ArrayList<PaymentType>());
        }
        if (languageType.getEmployeeTypeList() == null) {
            languageType.setEmployeeTypeList(new ArrayList<EmployeeType>());
        }
        if (languageType.getProductTypeList() == null) {
            languageType.setProductTypeList(new ArrayList<ProductType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageType referencedType = languageType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                languageType.setReferencedType(referencedType);
            }
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : languageType.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            languageType.setEmployeeList(attachedEmployeeList);
            List<PriceType> attachedPriceTypeList = new ArrayList<PriceType>();
            for (PriceType priceTypeListPriceTypeToAttach : languageType.getPriceTypeList()) {
                priceTypeListPriceTypeToAttach = em.getReference(priceTypeListPriceTypeToAttach.getClass(), priceTypeListPriceTypeToAttach.getId());
                attachedPriceTypeList.add(priceTypeListPriceTypeToAttach);
            }
            languageType.setPriceTypeList(attachedPriceTypeList);
            List<LanguageType> attachedLanguageTypeList = new ArrayList<LanguageType>();
            for (LanguageType languageTypeListLanguageTypeToAttach : languageType.getLanguageTypeList()) {
                languageTypeListLanguageTypeToAttach = em.getReference(languageTypeListLanguageTypeToAttach.getClass(), languageTypeListLanguageTypeToAttach.getId());
                attachedLanguageTypeList.add(languageTypeListLanguageTypeToAttach);
            }
            languageType.setLanguageTypeList(attachedLanguageTypeList);
            List<SexType> attachedSexTypeList = new ArrayList<SexType>();
            for (SexType sexTypeListSexTypeToAttach : languageType.getSexTypeList()) {
                sexTypeListSexTypeToAttach = em.getReference(sexTypeListSexTypeToAttach.getClass(), sexTypeListSexTypeToAttach.getId());
                attachedSexTypeList.add(sexTypeListSexTypeToAttach);
            }
            languageType.setSexTypeList(attachedSexTypeList);
            List<PaymentType> attachedPaymentTypeList = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListPaymentTypeToAttach : languageType.getPaymentTypeList()) {
                paymentTypeListPaymentTypeToAttach = em.getReference(paymentTypeListPaymentTypeToAttach.getClass(), paymentTypeListPaymentTypeToAttach.getId());
                attachedPaymentTypeList.add(paymentTypeListPaymentTypeToAttach);
            }
            languageType.setPaymentTypeList(attachedPaymentTypeList);
            List<EmployeeType> attachedEmployeeTypeList = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListEmployeeTypeToAttach : languageType.getEmployeeTypeList()) {
                employeeTypeListEmployeeTypeToAttach = em.getReference(employeeTypeListEmployeeTypeToAttach.getClass(), employeeTypeListEmployeeTypeToAttach.getId());
                attachedEmployeeTypeList.add(employeeTypeListEmployeeTypeToAttach);
            }
            languageType.setEmployeeTypeList(attachedEmployeeTypeList);
            List<ProductType> attachedProductTypeList = new ArrayList<ProductType>();
            for (ProductType productTypeListProductTypeToAttach : languageType.getProductTypeList()) {
                productTypeListProductTypeToAttach = em.getReference(productTypeListProductTypeToAttach.getClass(), productTypeListProductTypeToAttach.getId());
                attachedProductTypeList.add(productTypeListProductTypeToAttach);
            }
            languageType.setProductTypeList(attachedProductTypeList);
            em.persist(languageType);
            if (referencedType != null) {
                referencedType.getLanguageTypeList().add(languageType);
                referencedType = em.merge(referencedType);
            }
            for (Employee employeeListEmployee : languageType.getEmployeeList()) {
                LanguageType oldLanguageTypeOfEmployeeListEmployee = employeeListEmployee.getLanguageType();
                employeeListEmployee.setLanguageType(languageType);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldLanguageTypeOfEmployeeListEmployee != null) {
                    oldLanguageTypeOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldLanguageTypeOfEmployeeListEmployee = em.merge(oldLanguageTypeOfEmployeeListEmployee);
                }
            }
            for (PriceType priceTypeListPriceType : languageType.getPriceTypeList()) {
                LanguageType oldLanguageTypeOfPriceTypeListPriceType = priceTypeListPriceType.getLanguageType();
                priceTypeListPriceType.setLanguageType(languageType);
                priceTypeListPriceType = em.merge(priceTypeListPriceType);
                if (oldLanguageTypeOfPriceTypeListPriceType != null) {
                    oldLanguageTypeOfPriceTypeListPriceType.getPriceTypeList().remove(priceTypeListPriceType);
                    oldLanguageTypeOfPriceTypeListPriceType = em.merge(oldLanguageTypeOfPriceTypeListPriceType);
                }
            }
            for (LanguageType languageTypeListLanguageType : languageType.getLanguageTypeList()) {
                LanguageType oldReferencedTypeOfLanguageTypeListLanguageType = languageTypeListLanguageType.getReferencedType();
                languageTypeListLanguageType.setReferencedType(languageType);
                languageTypeListLanguageType = em.merge(languageTypeListLanguageType);
                if (oldReferencedTypeOfLanguageTypeListLanguageType != null) {
                    oldReferencedTypeOfLanguageTypeListLanguageType.getLanguageTypeList().remove(languageTypeListLanguageType);
                    oldReferencedTypeOfLanguageTypeListLanguageType = em.merge(oldReferencedTypeOfLanguageTypeListLanguageType);
                }
            }
            for (SexType sexTypeListSexType : languageType.getSexTypeList()) {
                LanguageType oldLanguageTypeOfSexTypeListSexType = sexTypeListSexType.getLanguageType();
                sexTypeListSexType.setLanguageType(languageType);
                sexTypeListSexType = em.merge(sexTypeListSexType);
                if (oldLanguageTypeOfSexTypeListSexType != null) {
                    oldLanguageTypeOfSexTypeListSexType.getSexTypeList().remove(sexTypeListSexType);
                    oldLanguageTypeOfSexTypeListSexType = em.merge(oldLanguageTypeOfSexTypeListSexType);
                }
            }
            for (PaymentType paymentTypeListPaymentType : languageType.getPaymentTypeList()) {
                LanguageType oldLanguageTypeOfPaymentTypeListPaymentType = paymentTypeListPaymentType.getLanguageType();
                paymentTypeListPaymentType.setLanguageType(languageType);
                paymentTypeListPaymentType = em.merge(paymentTypeListPaymentType);
                if (oldLanguageTypeOfPaymentTypeListPaymentType != null) {
                    oldLanguageTypeOfPaymentTypeListPaymentType.getPaymentTypeList().remove(paymentTypeListPaymentType);
                    oldLanguageTypeOfPaymentTypeListPaymentType = em.merge(oldLanguageTypeOfPaymentTypeListPaymentType);
                }
            }
            for (EmployeeType employeeTypeListEmployeeType : languageType.getEmployeeTypeList()) {
                LanguageType oldLanguageTypeOfEmployeeTypeListEmployeeType = employeeTypeListEmployeeType.getLanguageType();
                employeeTypeListEmployeeType.setLanguageType(languageType);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
                if (oldLanguageTypeOfEmployeeTypeListEmployeeType != null) {
                    oldLanguageTypeOfEmployeeTypeListEmployeeType.getEmployeeTypeList().remove(employeeTypeListEmployeeType);
                    oldLanguageTypeOfEmployeeTypeListEmployeeType = em.merge(oldLanguageTypeOfEmployeeTypeListEmployeeType);
                }
            }
            for (ProductType productTypeListProductType : languageType.getProductTypeList()) {
                LanguageType oldLanguageTypeOfProductTypeListProductType = productTypeListProductType.getLanguageType();
                productTypeListProductType.setLanguageType(languageType);
                productTypeListProductType = em.merge(productTypeListProductType);
                if (oldLanguageTypeOfProductTypeListProductType != null) {
                    oldLanguageTypeOfProductTypeListProductType.getProductTypeList().remove(productTypeListProductType);
                    oldLanguageTypeOfProductTypeListProductType = em.merge(oldLanguageTypeOfProductTypeListProductType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LanguageType languageType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LanguageType persistentLanguageType = em.find(LanguageType.class, languageType.getId());
            LanguageType referencedTypeOld = persistentLanguageType.getReferencedType();
            LanguageType referencedTypeNew = languageType.getReferencedType();
            List<Employee> employeeListOld = persistentLanguageType.getEmployeeList();
            List<Employee> employeeListNew = languageType.getEmployeeList();
            List<PriceType> priceTypeListOld = persistentLanguageType.getPriceTypeList();
            List<PriceType> priceTypeListNew = languageType.getPriceTypeList();
            List<LanguageType> languageTypeListOld = persistentLanguageType.getLanguageTypeList();
            List<LanguageType> languageTypeListNew = languageType.getLanguageTypeList();
            List<SexType> sexTypeListOld = persistentLanguageType.getSexTypeList();
            List<SexType> sexTypeListNew = languageType.getSexTypeList();
            List<PaymentType> paymentTypeListOld = persistentLanguageType.getPaymentTypeList();
            List<PaymentType> paymentTypeListNew = languageType.getPaymentTypeList();
            List<EmployeeType> employeeTypeListOld = persistentLanguageType.getEmployeeTypeList();
            List<EmployeeType> employeeTypeListNew = languageType.getEmployeeTypeList();
            List<ProductType> productTypeListOld = persistentLanguageType.getProductTypeList();
            List<ProductType> productTypeListNew = languageType.getProductTypeList();
            List<String> illegalOrphanMessages = null;
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its languageType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                languageType.setReferencedType(referencedTypeNew);
            }
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            languageType.setEmployeeList(employeeListNew);
            List<PriceType> attachedPriceTypeListNew = new ArrayList<PriceType>();
            for (PriceType priceTypeListNewPriceTypeToAttach : priceTypeListNew) {
                priceTypeListNewPriceTypeToAttach = em.getReference(priceTypeListNewPriceTypeToAttach.getClass(), priceTypeListNewPriceTypeToAttach.getId());
                attachedPriceTypeListNew.add(priceTypeListNewPriceTypeToAttach);
            }
            priceTypeListNew = attachedPriceTypeListNew;
            languageType.setPriceTypeList(priceTypeListNew);
            List<LanguageType> attachedLanguageTypeListNew = new ArrayList<LanguageType>();
            for (LanguageType languageTypeListNewLanguageTypeToAttach : languageTypeListNew) {
                languageTypeListNewLanguageTypeToAttach = em.getReference(languageTypeListNewLanguageTypeToAttach.getClass(), languageTypeListNewLanguageTypeToAttach.getId());
                attachedLanguageTypeListNew.add(languageTypeListNewLanguageTypeToAttach);
            }
            languageTypeListNew = attachedLanguageTypeListNew;
            languageType.setLanguageTypeList(languageTypeListNew);
            List<SexType> attachedSexTypeListNew = new ArrayList<SexType>();
            for (SexType sexTypeListNewSexTypeToAttach : sexTypeListNew) {
                sexTypeListNewSexTypeToAttach = em.getReference(sexTypeListNewSexTypeToAttach.getClass(), sexTypeListNewSexTypeToAttach.getId());
                attachedSexTypeListNew.add(sexTypeListNewSexTypeToAttach);
            }
            sexTypeListNew = attachedSexTypeListNew;
            languageType.setSexTypeList(sexTypeListNew);
            List<PaymentType> attachedPaymentTypeListNew = new ArrayList<PaymentType>();
            for (PaymentType paymentTypeListNewPaymentTypeToAttach : paymentTypeListNew) {
                paymentTypeListNewPaymentTypeToAttach = em.getReference(paymentTypeListNewPaymentTypeToAttach.getClass(), paymentTypeListNewPaymentTypeToAttach.getId());
                attachedPaymentTypeListNew.add(paymentTypeListNewPaymentTypeToAttach);
            }
            paymentTypeListNew = attachedPaymentTypeListNew;
            languageType.setPaymentTypeList(paymentTypeListNew);
            List<EmployeeType> attachedEmployeeTypeListNew = new ArrayList<EmployeeType>();
            for (EmployeeType employeeTypeListNewEmployeeTypeToAttach : employeeTypeListNew) {
                employeeTypeListNewEmployeeTypeToAttach = em.getReference(employeeTypeListNewEmployeeTypeToAttach.getClass(), employeeTypeListNewEmployeeTypeToAttach.getId());
                attachedEmployeeTypeListNew.add(employeeTypeListNewEmployeeTypeToAttach);
            }
            employeeTypeListNew = attachedEmployeeTypeListNew;
            languageType.setEmployeeTypeList(employeeTypeListNew);
            List<ProductType> attachedProductTypeListNew = new ArrayList<ProductType>();
            for (ProductType productTypeListNewProductTypeToAttach : productTypeListNew) {
                productTypeListNewProductTypeToAttach = em.getReference(productTypeListNewProductTypeToAttach.getClass(), productTypeListNewProductTypeToAttach.getId());
                attachedProductTypeListNew.add(productTypeListNewProductTypeToAttach);
            }
            productTypeListNew = attachedProductTypeListNew;
            languageType.setProductTypeList(productTypeListNew);
            languageType = em.merge(languageType);
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getLanguageTypeList().remove(languageType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getLanguageTypeList().add(languageType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    LanguageType oldLanguageTypeOfEmployeeListNewEmployee = employeeListNewEmployee.getLanguageType();
                    employeeListNewEmployee.setLanguageType(languageType);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldLanguageTypeOfEmployeeListNewEmployee != null && !oldLanguageTypeOfEmployeeListNewEmployee.equals(languageType)) {
                        oldLanguageTypeOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldLanguageTypeOfEmployeeListNewEmployee = em.merge(oldLanguageTypeOfEmployeeListNewEmployee);
                    }
                }
            }
            for (PriceType priceTypeListOldPriceType : priceTypeListOld) {
                if (!priceTypeListNew.contains(priceTypeListOldPriceType)) {
                    priceTypeListOldPriceType.setLanguageType(null);
                    priceTypeListOldPriceType = em.merge(priceTypeListOldPriceType);
                }
            }
            for (PriceType priceTypeListNewPriceType : priceTypeListNew) {
                if (!priceTypeListOld.contains(priceTypeListNewPriceType)) {
                    LanguageType oldLanguageTypeOfPriceTypeListNewPriceType = priceTypeListNewPriceType.getLanguageType();
                    priceTypeListNewPriceType.setLanguageType(languageType);
                    priceTypeListNewPriceType = em.merge(priceTypeListNewPriceType);
                    if (oldLanguageTypeOfPriceTypeListNewPriceType != null && !oldLanguageTypeOfPriceTypeListNewPriceType.equals(languageType)) {
                        oldLanguageTypeOfPriceTypeListNewPriceType.getPriceTypeList().remove(priceTypeListNewPriceType);
                        oldLanguageTypeOfPriceTypeListNewPriceType = em.merge(oldLanguageTypeOfPriceTypeListNewPriceType);
                    }
                }
            }
            for (LanguageType languageTypeListOldLanguageType : languageTypeListOld) {
                if (!languageTypeListNew.contains(languageTypeListOldLanguageType)) {
                    languageTypeListOldLanguageType.setReferencedType(null);
                    languageTypeListOldLanguageType = em.merge(languageTypeListOldLanguageType);
                }
            }
            for (LanguageType languageTypeListNewLanguageType : languageTypeListNew) {
                if (!languageTypeListOld.contains(languageTypeListNewLanguageType)) {
                    LanguageType oldReferencedTypeOfLanguageTypeListNewLanguageType = languageTypeListNewLanguageType.getReferencedType();
                    languageTypeListNewLanguageType.setReferencedType(languageType);
                    languageTypeListNewLanguageType = em.merge(languageTypeListNewLanguageType);
                    if (oldReferencedTypeOfLanguageTypeListNewLanguageType != null && !oldReferencedTypeOfLanguageTypeListNewLanguageType.equals(languageType)) {
                        oldReferencedTypeOfLanguageTypeListNewLanguageType.getLanguageTypeList().remove(languageTypeListNewLanguageType);
                        oldReferencedTypeOfLanguageTypeListNewLanguageType = em.merge(oldReferencedTypeOfLanguageTypeListNewLanguageType);
                    }
                }
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    sexTypeListOldSexType.setLanguageType(null);
                    sexTypeListOldSexType = em.merge(sexTypeListOldSexType);
                }
            }
            for (SexType sexTypeListNewSexType : sexTypeListNew) {
                if (!sexTypeListOld.contains(sexTypeListNewSexType)) {
                    LanguageType oldLanguageTypeOfSexTypeListNewSexType = sexTypeListNewSexType.getLanguageType();
                    sexTypeListNewSexType.setLanguageType(languageType);
                    sexTypeListNewSexType = em.merge(sexTypeListNewSexType);
                    if (oldLanguageTypeOfSexTypeListNewSexType != null && !oldLanguageTypeOfSexTypeListNewSexType.equals(languageType)) {
                        oldLanguageTypeOfSexTypeListNewSexType.getSexTypeList().remove(sexTypeListNewSexType);
                        oldLanguageTypeOfSexTypeListNewSexType = em.merge(oldLanguageTypeOfSexTypeListNewSexType);
                    }
                }
            }
            for (PaymentType paymentTypeListOldPaymentType : paymentTypeListOld) {
                if (!paymentTypeListNew.contains(paymentTypeListOldPaymentType)) {
                    paymentTypeListOldPaymentType.setLanguageType(null);
                    paymentTypeListOldPaymentType = em.merge(paymentTypeListOldPaymentType);
                }
            }
            for (PaymentType paymentTypeListNewPaymentType : paymentTypeListNew) {
                if (!paymentTypeListOld.contains(paymentTypeListNewPaymentType)) {
                    LanguageType oldLanguageTypeOfPaymentTypeListNewPaymentType = paymentTypeListNewPaymentType.getLanguageType();
                    paymentTypeListNewPaymentType.setLanguageType(languageType);
                    paymentTypeListNewPaymentType = em.merge(paymentTypeListNewPaymentType);
                    if (oldLanguageTypeOfPaymentTypeListNewPaymentType != null && !oldLanguageTypeOfPaymentTypeListNewPaymentType.equals(languageType)) {
                        oldLanguageTypeOfPaymentTypeListNewPaymentType.getPaymentTypeList().remove(paymentTypeListNewPaymentType);
                        oldLanguageTypeOfPaymentTypeListNewPaymentType = em.merge(oldLanguageTypeOfPaymentTypeListNewPaymentType);
                    }
                }
            }
            for (EmployeeType employeeTypeListOldEmployeeType : employeeTypeListOld) {
                if (!employeeTypeListNew.contains(employeeTypeListOldEmployeeType)) {
                    employeeTypeListOldEmployeeType.setLanguageType(null);
                    employeeTypeListOldEmployeeType = em.merge(employeeTypeListOldEmployeeType);
                }
            }
            for (EmployeeType employeeTypeListNewEmployeeType : employeeTypeListNew) {
                if (!employeeTypeListOld.contains(employeeTypeListNewEmployeeType)) {
                    LanguageType oldLanguageTypeOfEmployeeTypeListNewEmployeeType = employeeTypeListNewEmployeeType.getLanguageType();
                    employeeTypeListNewEmployeeType.setLanguageType(languageType);
                    employeeTypeListNewEmployeeType = em.merge(employeeTypeListNewEmployeeType);
                    if (oldLanguageTypeOfEmployeeTypeListNewEmployeeType != null && !oldLanguageTypeOfEmployeeTypeListNewEmployeeType.equals(languageType)) {
                        oldLanguageTypeOfEmployeeTypeListNewEmployeeType.getEmployeeTypeList().remove(employeeTypeListNewEmployeeType);
                        oldLanguageTypeOfEmployeeTypeListNewEmployeeType = em.merge(oldLanguageTypeOfEmployeeTypeListNewEmployeeType);
                    }
                }
            }
            for (ProductType productTypeListOldProductType : productTypeListOld) {
                if (!productTypeListNew.contains(productTypeListOldProductType)) {
                    productTypeListOldProductType.setLanguageType(null);
                    productTypeListOldProductType = em.merge(productTypeListOldProductType);
                }
            }
            for (ProductType productTypeListNewProductType : productTypeListNew) {
                if (!productTypeListOld.contains(productTypeListNewProductType)) {
                    LanguageType oldLanguageTypeOfProductTypeListNewProductType = productTypeListNewProductType.getLanguageType();
                    productTypeListNewProductType.setLanguageType(languageType);
                    productTypeListNewProductType = em.merge(productTypeListNewProductType);
                    if (oldLanguageTypeOfProductTypeListNewProductType != null && !oldLanguageTypeOfProductTypeListNewProductType.equals(languageType)) {
                        oldLanguageTypeOfProductTypeListNewProductType.getProductTypeList().remove(productTypeListNewProductType);
                        oldLanguageTypeOfProductTypeListNewProductType = em.merge(oldLanguageTypeOfProductTypeListNewProductType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = languageType.getId();
                if (findLanguageType(id) == null) {
                    throw new NonexistentEntityException("The languageType with id " + id + " no longer exists.");
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
            LanguageType languageType;
            try {
                languageType = em.getReference(LanguageType.class, id);
                languageType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The languageType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Employee> employeeListOrphanCheck = languageType.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable languageType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            LanguageType referencedType = languageType.getReferencedType();
            if (referencedType != null) {
                referencedType.getLanguageTypeList().remove(languageType);
                referencedType = em.merge(referencedType);
            }
            List<PriceType> priceTypeList = languageType.getPriceTypeList();
            for (PriceType priceTypeListPriceType : priceTypeList) {
                priceTypeListPriceType.setLanguageType(null);
                priceTypeListPriceType = em.merge(priceTypeListPriceType);
            }
            List<LanguageType> languageTypeList = languageType.getLanguageTypeList();
            for (LanguageType languageTypeListLanguageType : languageTypeList) {
                languageTypeListLanguageType.setReferencedType(null);
                languageTypeListLanguageType = em.merge(languageTypeListLanguageType);
            }
            List<SexType> sexTypeList = languageType.getSexTypeList();
            for (SexType sexTypeListSexType : sexTypeList) {
                sexTypeListSexType.setLanguageType(null);
                sexTypeListSexType = em.merge(sexTypeListSexType);
            }
            List<PaymentType> paymentTypeList = languageType.getPaymentTypeList();
            for (PaymentType paymentTypeListPaymentType : paymentTypeList) {
                paymentTypeListPaymentType.setLanguageType(null);
                paymentTypeListPaymentType = em.merge(paymentTypeListPaymentType);
            }
            List<EmployeeType> employeeTypeList = languageType.getEmployeeTypeList();
            for (EmployeeType employeeTypeListEmployeeType : employeeTypeList) {
                employeeTypeListEmployeeType.setLanguageType(null);
                employeeTypeListEmployeeType = em.merge(employeeTypeListEmployeeType);
            }
            List<ProductType> productTypeList = languageType.getProductTypeList();
            for (ProductType productTypeListProductType : productTypeList) {
                productTypeListProductType.setLanguageType(null);
                productTypeListProductType = em.merge(productTypeListProductType);
            }
            em.remove(languageType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LanguageType> findLanguageTypeEntities() {
        return findLanguageTypeEntities(true, -1, -1);
    }

    public List<LanguageType> findLanguageTypeEntities(int maxResults, int firstResult) {
        return findLanguageTypeEntities(false, maxResults, firstResult);
    }

    private List<LanguageType> findLanguageTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LanguageType.class));
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

    public LanguageType findLanguageType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LanguageType.class, id);
        } finally {
            em.close();
        }
    }

    public int getLanguageTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LanguageType> rt = cq.from(LanguageType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
