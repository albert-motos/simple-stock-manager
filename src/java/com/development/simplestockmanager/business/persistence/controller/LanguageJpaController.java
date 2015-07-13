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
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.EmployeeTypeTranslation;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.SexTypeTranslation;
import com.development.simplestockmanager.business.persistence.PriceTypeTranslation;
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import com.development.simplestockmanager.business.persistence.PaymentTypeTranslation;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class LanguageJpaController implements Serializable {

    public LanguageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Language language) {
        if (language.getEmployeeTypeTranslationList() == null) {
            language.setEmployeeTypeTranslationList(new ArrayList<EmployeeTypeTranslation>());
        }
        if (language.getLanguageList() == null) {
            language.setLanguageList(new ArrayList<Language>());
        }
        if (language.getEmployeeList() == null) {
            language.setEmployeeList(new ArrayList<Employee>());
        }
        if (language.getSexTypeTranslationList() == null) {
            language.setSexTypeTranslationList(new ArrayList<SexTypeTranslation>());
        }
        if (language.getPriceTypeTranslationList() == null) {
            language.setPriceTypeTranslationList(new ArrayList<PriceTypeTranslation>());
        }
        if (language.getProductTypeTranslationList() == null) {
            language.setProductTypeTranslationList(new ArrayList<ProductTypeTranslation>());
        }
        if (language.getPaymentTypeTranslationList() == null) {
            language.setPaymentTypeTranslationList(new ArrayList<PaymentTypeTranslation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language reference = language.getReference();
            if (reference != null) {
                reference = em.getReference(reference.getClass(), reference.getId());
                language.setReference(reference);
            }
            List<EmployeeTypeTranslation> attachedEmployeeTypeTranslationList = new ArrayList<EmployeeTypeTranslation>();
            for (EmployeeTypeTranslation employeeTypeTranslationListEmployeeTypeTranslationToAttach : language.getEmployeeTypeTranslationList()) {
                employeeTypeTranslationListEmployeeTypeTranslationToAttach = em.getReference(employeeTypeTranslationListEmployeeTypeTranslationToAttach.getClass(), employeeTypeTranslationListEmployeeTypeTranslationToAttach.getId());
                attachedEmployeeTypeTranslationList.add(employeeTypeTranslationListEmployeeTypeTranslationToAttach);
            }
            language.setEmployeeTypeTranslationList(attachedEmployeeTypeTranslationList);
            List<Language> attachedLanguageList = new ArrayList<Language>();
            for (Language languageListLanguageToAttach : language.getLanguageList()) {
                languageListLanguageToAttach = em.getReference(languageListLanguageToAttach.getClass(), languageListLanguageToAttach.getId());
                attachedLanguageList.add(languageListLanguageToAttach);
            }
            language.setLanguageList(attachedLanguageList);
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : language.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getId());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            language.setEmployeeList(attachedEmployeeList);
            List<SexTypeTranslation> attachedSexTypeTranslationList = new ArrayList<SexTypeTranslation>();
            for (SexTypeTranslation sexTypeTranslationListSexTypeTranslationToAttach : language.getSexTypeTranslationList()) {
                sexTypeTranslationListSexTypeTranslationToAttach = em.getReference(sexTypeTranslationListSexTypeTranslationToAttach.getClass(), sexTypeTranslationListSexTypeTranslationToAttach.getId());
                attachedSexTypeTranslationList.add(sexTypeTranslationListSexTypeTranslationToAttach);
            }
            language.setSexTypeTranslationList(attachedSexTypeTranslationList);
            List<PriceTypeTranslation> attachedPriceTypeTranslationList = new ArrayList<PriceTypeTranslation>();
            for (PriceTypeTranslation priceTypeTranslationListPriceTypeTranslationToAttach : language.getPriceTypeTranslationList()) {
                priceTypeTranslationListPriceTypeTranslationToAttach = em.getReference(priceTypeTranslationListPriceTypeTranslationToAttach.getClass(), priceTypeTranslationListPriceTypeTranslationToAttach.getId());
                attachedPriceTypeTranslationList.add(priceTypeTranslationListPriceTypeTranslationToAttach);
            }
            language.setPriceTypeTranslationList(attachedPriceTypeTranslationList);
            List<ProductTypeTranslation> attachedProductTypeTranslationList = new ArrayList<ProductTypeTranslation>();
            for (ProductTypeTranslation productTypeTranslationListProductTypeTranslationToAttach : language.getProductTypeTranslationList()) {
                productTypeTranslationListProductTypeTranslationToAttach = em.getReference(productTypeTranslationListProductTypeTranslationToAttach.getClass(), productTypeTranslationListProductTypeTranslationToAttach.getId());
                attachedProductTypeTranslationList.add(productTypeTranslationListProductTypeTranslationToAttach);
            }
            language.setProductTypeTranslationList(attachedProductTypeTranslationList);
            List<PaymentTypeTranslation> attachedPaymentTypeTranslationList = new ArrayList<PaymentTypeTranslation>();
            for (PaymentTypeTranslation paymentTypeTranslationListPaymentTypeTranslationToAttach : language.getPaymentTypeTranslationList()) {
                paymentTypeTranslationListPaymentTypeTranslationToAttach = em.getReference(paymentTypeTranslationListPaymentTypeTranslationToAttach.getClass(), paymentTypeTranslationListPaymentTypeTranslationToAttach.getId());
                attachedPaymentTypeTranslationList.add(paymentTypeTranslationListPaymentTypeTranslationToAttach);
            }
            language.setPaymentTypeTranslationList(attachedPaymentTypeTranslationList);
            em.persist(language);
            if (reference != null) {
                reference.getLanguageList().add(language);
                reference = em.merge(reference);
            }
            for (EmployeeTypeTranslation employeeTypeTranslationListEmployeeTypeTranslation : language.getEmployeeTypeTranslationList()) {
                Language oldLanguageOfEmployeeTypeTranslationListEmployeeTypeTranslation = employeeTypeTranslationListEmployeeTypeTranslation.getLanguage();
                employeeTypeTranslationListEmployeeTypeTranslation.setLanguage(language);
                employeeTypeTranslationListEmployeeTypeTranslation = em.merge(employeeTypeTranslationListEmployeeTypeTranslation);
                if (oldLanguageOfEmployeeTypeTranslationListEmployeeTypeTranslation != null) {
                    oldLanguageOfEmployeeTypeTranslationListEmployeeTypeTranslation.getEmployeeTypeTranslationList().remove(employeeTypeTranslationListEmployeeTypeTranslation);
                    oldLanguageOfEmployeeTypeTranslationListEmployeeTypeTranslation = em.merge(oldLanguageOfEmployeeTypeTranslationListEmployeeTypeTranslation);
                }
            }
            for (Language languageListLanguage : language.getLanguageList()) {
                Language oldReferenceOfLanguageListLanguage = languageListLanguage.getReference();
                languageListLanguage.setReference(language);
                languageListLanguage = em.merge(languageListLanguage);
                if (oldReferenceOfLanguageListLanguage != null) {
                    oldReferenceOfLanguageListLanguage.getLanguageList().remove(languageListLanguage);
                    oldReferenceOfLanguageListLanguage = em.merge(oldReferenceOfLanguageListLanguage);
                }
            }
            for (Employee employeeListEmployee : language.getEmployeeList()) {
                Language oldLanguageTypeOfEmployeeListEmployee = employeeListEmployee.getLanguageType();
                employeeListEmployee.setLanguageType(language);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldLanguageTypeOfEmployeeListEmployee != null) {
                    oldLanguageTypeOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldLanguageTypeOfEmployeeListEmployee = em.merge(oldLanguageTypeOfEmployeeListEmployee);
                }
            }
            for (SexTypeTranslation sexTypeTranslationListSexTypeTranslation : language.getSexTypeTranslationList()) {
                Language oldLanguageOfSexTypeTranslationListSexTypeTranslation = sexTypeTranslationListSexTypeTranslation.getLanguage();
                sexTypeTranslationListSexTypeTranslation.setLanguage(language);
                sexTypeTranslationListSexTypeTranslation = em.merge(sexTypeTranslationListSexTypeTranslation);
                if (oldLanguageOfSexTypeTranslationListSexTypeTranslation != null) {
                    oldLanguageOfSexTypeTranslationListSexTypeTranslation.getSexTypeTranslationList().remove(sexTypeTranslationListSexTypeTranslation);
                    oldLanguageOfSexTypeTranslationListSexTypeTranslation = em.merge(oldLanguageOfSexTypeTranslationListSexTypeTranslation);
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListPriceTypeTranslation : language.getPriceTypeTranslationList()) {
                Language oldLanguageOfPriceTypeTranslationListPriceTypeTranslation = priceTypeTranslationListPriceTypeTranslation.getLanguage();
                priceTypeTranslationListPriceTypeTranslation.setLanguage(language);
                priceTypeTranslationListPriceTypeTranslation = em.merge(priceTypeTranslationListPriceTypeTranslation);
                if (oldLanguageOfPriceTypeTranslationListPriceTypeTranslation != null) {
                    oldLanguageOfPriceTypeTranslationListPriceTypeTranslation.getPriceTypeTranslationList().remove(priceTypeTranslationListPriceTypeTranslation);
                    oldLanguageOfPriceTypeTranslationListPriceTypeTranslation = em.merge(oldLanguageOfPriceTypeTranslationListPriceTypeTranslation);
                }
            }
            for (ProductTypeTranslation productTypeTranslationListProductTypeTranslation : language.getProductTypeTranslationList()) {
                Language oldLanguageOfProductTypeTranslationListProductTypeTranslation = productTypeTranslationListProductTypeTranslation.getLanguage();
                productTypeTranslationListProductTypeTranslation.setLanguage(language);
                productTypeTranslationListProductTypeTranslation = em.merge(productTypeTranslationListProductTypeTranslation);
                if (oldLanguageOfProductTypeTranslationListProductTypeTranslation != null) {
                    oldLanguageOfProductTypeTranslationListProductTypeTranslation.getProductTypeTranslationList().remove(productTypeTranslationListProductTypeTranslation);
                    oldLanguageOfProductTypeTranslationListProductTypeTranslation = em.merge(oldLanguageOfProductTypeTranslationListProductTypeTranslation);
                }
            }
            for (PaymentTypeTranslation paymentTypeTranslationListPaymentTypeTranslation : language.getPaymentTypeTranslationList()) {
                Language oldLanguageOfPaymentTypeTranslationListPaymentTypeTranslation = paymentTypeTranslationListPaymentTypeTranslation.getLanguage();
                paymentTypeTranslationListPaymentTypeTranslation.setLanguage(language);
                paymentTypeTranslationListPaymentTypeTranslation = em.merge(paymentTypeTranslationListPaymentTypeTranslation);
                if (oldLanguageOfPaymentTypeTranslationListPaymentTypeTranslation != null) {
                    oldLanguageOfPaymentTypeTranslationListPaymentTypeTranslation.getPaymentTypeTranslationList().remove(paymentTypeTranslationListPaymentTypeTranslation);
                    oldLanguageOfPaymentTypeTranslationListPaymentTypeTranslation = em.merge(oldLanguageOfPaymentTypeTranslationListPaymentTypeTranslation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Language language) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Language persistentLanguage = em.find(Language.class, language.getId());
            Language referenceOld = persistentLanguage.getReference();
            Language referenceNew = language.getReference();
            List<EmployeeTypeTranslation> employeeTypeTranslationListOld = persistentLanguage.getEmployeeTypeTranslationList();
            List<EmployeeTypeTranslation> employeeTypeTranslationListNew = language.getEmployeeTypeTranslationList();
            List<Language> languageListOld = persistentLanguage.getLanguageList();
            List<Language> languageListNew = language.getLanguageList();
            List<Employee> employeeListOld = persistentLanguage.getEmployeeList();
            List<Employee> employeeListNew = language.getEmployeeList();
            List<SexTypeTranslation> sexTypeTranslationListOld = persistentLanguage.getSexTypeTranslationList();
            List<SexTypeTranslation> sexTypeTranslationListNew = language.getSexTypeTranslationList();
            List<PriceTypeTranslation> priceTypeTranslationListOld = persistentLanguage.getPriceTypeTranslationList();
            List<PriceTypeTranslation> priceTypeTranslationListNew = language.getPriceTypeTranslationList();
            List<ProductTypeTranslation> productTypeTranslationListOld = persistentLanguage.getProductTypeTranslationList();
            List<ProductTypeTranslation> productTypeTranslationListNew = language.getProductTypeTranslationList();
            List<PaymentTypeTranslation> paymentTypeTranslationListOld = persistentLanguage.getPaymentTypeTranslationList();
            List<PaymentTypeTranslation> paymentTypeTranslationListNew = language.getPaymentTypeTranslationList();
            List<String> illegalOrphanMessages = null;
            for (EmployeeTypeTranslation employeeTypeTranslationListOldEmployeeTypeTranslation : employeeTypeTranslationListOld) {
                if (!employeeTypeTranslationListNew.contains(employeeTypeTranslationListOldEmployeeTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeTypeTranslation " + employeeTypeTranslationListOldEmployeeTypeTranslation + " since its language field is not nullable.");
                }
            }
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employee " + employeeListOldEmployee + " since its languageType field is not nullable.");
                }
            }
            for (SexTypeTranslation sexTypeTranslationListOldSexTypeTranslation : sexTypeTranslationListOld) {
                if (!sexTypeTranslationListNew.contains(sexTypeTranslationListOldSexTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexTypeTranslation " + sexTypeTranslationListOldSexTypeTranslation + " since its language field is not nullable.");
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListOldPriceTypeTranslation : priceTypeTranslationListOld) {
                if (!priceTypeTranslationListNew.contains(priceTypeTranslationListOldPriceTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PriceTypeTranslation " + priceTypeTranslationListOldPriceTypeTranslation + " since its language field is not nullable.");
                }
            }
            for (ProductTypeTranslation productTypeTranslationListOldProductTypeTranslation : productTypeTranslationListOld) {
                if (!productTypeTranslationListNew.contains(productTypeTranslationListOldProductTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductTypeTranslation " + productTypeTranslationListOldProductTypeTranslation + " since its language field is not nullable.");
                }
            }
            for (PaymentTypeTranslation paymentTypeTranslationListOldPaymentTypeTranslation : paymentTypeTranslationListOld) {
                if (!paymentTypeTranslationListNew.contains(paymentTypeTranslationListOldPaymentTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaymentTypeTranslation " + paymentTypeTranslationListOldPaymentTypeTranslation + " since its language field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (referenceNew != null) {
                referenceNew = em.getReference(referenceNew.getClass(), referenceNew.getId());
                language.setReference(referenceNew);
            }
            List<EmployeeTypeTranslation> attachedEmployeeTypeTranslationListNew = new ArrayList<EmployeeTypeTranslation>();
            for (EmployeeTypeTranslation employeeTypeTranslationListNewEmployeeTypeTranslationToAttach : employeeTypeTranslationListNew) {
                employeeTypeTranslationListNewEmployeeTypeTranslationToAttach = em.getReference(employeeTypeTranslationListNewEmployeeTypeTranslationToAttach.getClass(), employeeTypeTranslationListNewEmployeeTypeTranslationToAttach.getId());
                attachedEmployeeTypeTranslationListNew.add(employeeTypeTranslationListNewEmployeeTypeTranslationToAttach);
            }
            employeeTypeTranslationListNew = attachedEmployeeTypeTranslationListNew;
            language.setEmployeeTypeTranslationList(employeeTypeTranslationListNew);
            List<Language> attachedLanguageListNew = new ArrayList<Language>();
            for (Language languageListNewLanguageToAttach : languageListNew) {
                languageListNewLanguageToAttach = em.getReference(languageListNewLanguageToAttach.getClass(), languageListNewLanguageToAttach.getId());
                attachedLanguageListNew.add(languageListNewLanguageToAttach);
            }
            languageListNew = attachedLanguageListNew;
            language.setLanguageList(languageListNew);
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getId());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            language.setEmployeeList(employeeListNew);
            List<SexTypeTranslation> attachedSexTypeTranslationListNew = new ArrayList<SexTypeTranslation>();
            for (SexTypeTranslation sexTypeTranslationListNewSexTypeTranslationToAttach : sexTypeTranslationListNew) {
                sexTypeTranslationListNewSexTypeTranslationToAttach = em.getReference(sexTypeTranslationListNewSexTypeTranslationToAttach.getClass(), sexTypeTranslationListNewSexTypeTranslationToAttach.getId());
                attachedSexTypeTranslationListNew.add(sexTypeTranslationListNewSexTypeTranslationToAttach);
            }
            sexTypeTranslationListNew = attachedSexTypeTranslationListNew;
            language.setSexTypeTranslationList(sexTypeTranslationListNew);
            List<PriceTypeTranslation> attachedPriceTypeTranslationListNew = new ArrayList<PriceTypeTranslation>();
            for (PriceTypeTranslation priceTypeTranslationListNewPriceTypeTranslationToAttach : priceTypeTranslationListNew) {
                priceTypeTranslationListNewPriceTypeTranslationToAttach = em.getReference(priceTypeTranslationListNewPriceTypeTranslationToAttach.getClass(), priceTypeTranslationListNewPriceTypeTranslationToAttach.getId());
                attachedPriceTypeTranslationListNew.add(priceTypeTranslationListNewPriceTypeTranslationToAttach);
            }
            priceTypeTranslationListNew = attachedPriceTypeTranslationListNew;
            language.setPriceTypeTranslationList(priceTypeTranslationListNew);
            List<ProductTypeTranslation> attachedProductTypeTranslationListNew = new ArrayList<ProductTypeTranslation>();
            for (ProductTypeTranslation productTypeTranslationListNewProductTypeTranslationToAttach : productTypeTranslationListNew) {
                productTypeTranslationListNewProductTypeTranslationToAttach = em.getReference(productTypeTranslationListNewProductTypeTranslationToAttach.getClass(), productTypeTranslationListNewProductTypeTranslationToAttach.getId());
                attachedProductTypeTranslationListNew.add(productTypeTranslationListNewProductTypeTranslationToAttach);
            }
            productTypeTranslationListNew = attachedProductTypeTranslationListNew;
            language.setProductTypeTranslationList(productTypeTranslationListNew);
            List<PaymentTypeTranslation> attachedPaymentTypeTranslationListNew = new ArrayList<PaymentTypeTranslation>();
            for (PaymentTypeTranslation paymentTypeTranslationListNewPaymentTypeTranslationToAttach : paymentTypeTranslationListNew) {
                paymentTypeTranslationListNewPaymentTypeTranslationToAttach = em.getReference(paymentTypeTranslationListNewPaymentTypeTranslationToAttach.getClass(), paymentTypeTranslationListNewPaymentTypeTranslationToAttach.getId());
                attachedPaymentTypeTranslationListNew.add(paymentTypeTranslationListNewPaymentTypeTranslationToAttach);
            }
            paymentTypeTranslationListNew = attachedPaymentTypeTranslationListNew;
            language.setPaymentTypeTranslationList(paymentTypeTranslationListNew);
            language = em.merge(language);
            if (referenceOld != null && !referenceOld.equals(referenceNew)) {
                referenceOld.getLanguageList().remove(language);
                referenceOld = em.merge(referenceOld);
            }
            if (referenceNew != null && !referenceNew.equals(referenceOld)) {
                referenceNew.getLanguageList().add(language);
                referenceNew = em.merge(referenceNew);
            }
            for (EmployeeTypeTranslation employeeTypeTranslationListNewEmployeeTypeTranslation : employeeTypeTranslationListNew) {
                if (!employeeTypeTranslationListOld.contains(employeeTypeTranslationListNewEmployeeTypeTranslation)) {
                    Language oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation = employeeTypeTranslationListNewEmployeeTypeTranslation.getLanguage();
                    employeeTypeTranslationListNewEmployeeTypeTranslation.setLanguage(language);
                    employeeTypeTranslationListNewEmployeeTypeTranslation = em.merge(employeeTypeTranslationListNewEmployeeTypeTranslation);
                    if (oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation != null && !oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation.equals(language)) {
                        oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation.getEmployeeTypeTranslationList().remove(employeeTypeTranslationListNewEmployeeTypeTranslation);
                        oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation = em.merge(oldLanguageOfEmployeeTypeTranslationListNewEmployeeTypeTranslation);
                    }
                }
            }
            for (Language languageListOldLanguage : languageListOld) {
                if (!languageListNew.contains(languageListOldLanguage)) {
                    languageListOldLanguage.setReference(null);
                    languageListOldLanguage = em.merge(languageListOldLanguage);
                }
            }
            for (Language languageListNewLanguage : languageListNew) {
                if (!languageListOld.contains(languageListNewLanguage)) {
                    Language oldReferenceOfLanguageListNewLanguage = languageListNewLanguage.getReference();
                    languageListNewLanguage.setReference(language);
                    languageListNewLanguage = em.merge(languageListNewLanguage);
                    if (oldReferenceOfLanguageListNewLanguage != null && !oldReferenceOfLanguageListNewLanguage.equals(language)) {
                        oldReferenceOfLanguageListNewLanguage.getLanguageList().remove(languageListNewLanguage);
                        oldReferenceOfLanguageListNewLanguage = em.merge(oldReferenceOfLanguageListNewLanguage);
                    }
                }
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    Language oldLanguageTypeOfEmployeeListNewEmployee = employeeListNewEmployee.getLanguageType();
                    employeeListNewEmployee.setLanguageType(language);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldLanguageTypeOfEmployeeListNewEmployee != null && !oldLanguageTypeOfEmployeeListNewEmployee.equals(language)) {
                        oldLanguageTypeOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldLanguageTypeOfEmployeeListNewEmployee = em.merge(oldLanguageTypeOfEmployeeListNewEmployee);
                    }
                }
            }
            for (SexTypeTranslation sexTypeTranslationListNewSexTypeTranslation : sexTypeTranslationListNew) {
                if (!sexTypeTranslationListOld.contains(sexTypeTranslationListNewSexTypeTranslation)) {
                    Language oldLanguageOfSexTypeTranslationListNewSexTypeTranslation = sexTypeTranslationListNewSexTypeTranslation.getLanguage();
                    sexTypeTranslationListNewSexTypeTranslation.setLanguage(language);
                    sexTypeTranslationListNewSexTypeTranslation = em.merge(sexTypeTranslationListNewSexTypeTranslation);
                    if (oldLanguageOfSexTypeTranslationListNewSexTypeTranslation != null && !oldLanguageOfSexTypeTranslationListNewSexTypeTranslation.equals(language)) {
                        oldLanguageOfSexTypeTranslationListNewSexTypeTranslation.getSexTypeTranslationList().remove(sexTypeTranslationListNewSexTypeTranslation);
                        oldLanguageOfSexTypeTranslationListNewSexTypeTranslation = em.merge(oldLanguageOfSexTypeTranslationListNewSexTypeTranslation);
                    }
                }
            }
            for (PriceTypeTranslation priceTypeTranslationListNewPriceTypeTranslation : priceTypeTranslationListNew) {
                if (!priceTypeTranslationListOld.contains(priceTypeTranslationListNewPriceTypeTranslation)) {
                    Language oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation = priceTypeTranslationListNewPriceTypeTranslation.getLanguage();
                    priceTypeTranslationListNewPriceTypeTranslation.setLanguage(language);
                    priceTypeTranslationListNewPriceTypeTranslation = em.merge(priceTypeTranslationListNewPriceTypeTranslation);
                    if (oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation != null && !oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation.equals(language)) {
                        oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation.getPriceTypeTranslationList().remove(priceTypeTranslationListNewPriceTypeTranslation);
                        oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation = em.merge(oldLanguageOfPriceTypeTranslationListNewPriceTypeTranslation);
                    }
                }
            }
            for (ProductTypeTranslation productTypeTranslationListNewProductTypeTranslation : productTypeTranslationListNew) {
                if (!productTypeTranslationListOld.contains(productTypeTranslationListNewProductTypeTranslation)) {
                    Language oldLanguageOfProductTypeTranslationListNewProductTypeTranslation = productTypeTranslationListNewProductTypeTranslation.getLanguage();
                    productTypeTranslationListNewProductTypeTranslation.setLanguage(language);
                    productTypeTranslationListNewProductTypeTranslation = em.merge(productTypeTranslationListNewProductTypeTranslation);
                    if (oldLanguageOfProductTypeTranslationListNewProductTypeTranslation != null && !oldLanguageOfProductTypeTranslationListNewProductTypeTranslation.equals(language)) {
                        oldLanguageOfProductTypeTranslationListNewProductTypeTranslation.getProductTypeTranslationList().remove(productTypeTranslationListNewProductTypeTranslation);
                        oldLanguageOfProductTypeTranslationListNewProductTypeTranslation = em.merge(oldLanguageOfProductTypeTranslationListNewProductTypeTranslation);
                    }
                }
            }
            for (PaymentTypeTranslation paymentTypeTranslationListNewPaymentTypeTranslation : paymentTypeTranslationListNew) {
                if (!paymentTypeTranslationListOld.contains(paymentTypeTranslationListNewPaymentTypeTranslation)) {
                    Language oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation = paymentTypeTranslationListNewPaymentTypeTranslation.getLanguage();
                    paymentTypeTranslationListNewPaymentTypeTranslation.setLanguage(language);
                    paymentTypeTranslationListNewPaymentTypeTranslation = em.merge(paymentTypeTranslationListNewPaymentTypeTranslation);
                    if (oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation != null && !oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation.equals(language)) {
                        oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation.getPaymentTypeTranslationList().remove(paymentTypeTranslationListNewPaymentTypeTranslation);
                        oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation = em.merge(oldLanguageOfPaymentTypeTranslationListNewPaymentTypeTranslation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = language.getId();
                if (findLanguage(id) == null) {
                    throw new NonexistentEntityException("The language with id " + id + " no longer exists.");
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
            Language language;
            try {
                language = em.getReference(Language.class, id);
                language.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The language with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EmployeeTypeTranslation> employeeTypeTranslationListOrphanCheck = language.getEmployeeTypeTranslationList();
            for (EmployeeTypeTranslation employeeTypeTranslationListOrphanCheckEmployeeTypeTranslation : employeeTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the EmployeeTypeTranslation " + employeeTypeTranslationListOrphanCheckEmployeeTypeTranslation + " in its employeeTypeTranslationList field has a non-nullable language field.");
            }
            List<Employee> employeeListOrphanCheck = language.getEmployeeList();
            for (Employee employeeListOrphanCheckEmployee : employeeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the Employee " + employeeListOrphanCheckEmployee + " in its employeeList field has a non-nullable languageType field.");
            }
            List<SexTypeTranslation> sexTypeTranslationListOrphanCheck = language.getSexTypeTranslationList();
            for (SexTypeTranslation sexTypeTranslationListOrphanCheckSexTypeTranslation : sexTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the SexTypeTranslation " + sexTypeTranslationListOrphanCheckSexTypeTranslation + " in its sexTypeTranslationList field has a non-nullable language field.");
            }
            List<PriceTypeTranslation> priceTypeTranslationListOrphanCheck = language.getPriceTypeTranslationList();
            for (PriceTypeTranslation priceTypeTranslationListOrphanCheckPriceTypeTranslation : priceTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the PriceTypeTranslation " + priceTypeTranslationListOrphanCheckPriceTypeTranslation + " in its priceTypeTranslationList field has a non-nullable language field.");
            }
            List<ProductTypeTranslation> productTypeTranslationListOrphanCheck = language.getProductTypeTranslationList();
            for (ProductTypeTranslation productTypeTranslationListOrphanCheckProductTypeTranslation : productTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the ProductTypeTranslation " + productTypeTranslationListOrphanCheckProductTypeTranslation + " in its productTypeTranslationList field has a non-nullable language field.");
            }
            List<PaymentTypeTranslation> paymentTypeTranslationListOrphanCheck = language.getPaymentTypeTranslationList();
            for (PaymentTypeTranslation paymentTypeTranslationListOrphanCheckPaymentTypeTranslation : paymentTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Language (" + language + ") cannot be destroyed since the PaymentTypeTranslation " + paymentTypeTranslationListOrphanCheckPaymentTypeTranslation + " in its paymentTypeTranslationList field has a non-nullable language field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Language reference = language.getReference();
            if (reference != null) {
                reference.getLanguageList().remove(language);
                reference = em.merge(reference);
            }
            List<Language> languageList = language.getLanguageList();
            for (Language languageListLanguage : languageList) {
                languageListLanguage.setReference(null);
                languageListLanguage = em.merge(languageListLanguage);
            }
            em.remove(language);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Language> findLanguageEntities() {
        return findLanguageEntities(true, -1, -1);
    }

    public List<Language> findLanguageEntities(int maxResults, int firstResult) {
        return findLanguageEntities(false, maxResults, firstResult);
    }

    private List<Language> findLanguageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Language.class));
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

    public Language findLanguage(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Language.class, id);
        } finally {
            em.close();
        }
    }

    public int getLanguageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Language> rt = cq.from(Language.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
