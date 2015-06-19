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
import com.development.simplestockmanager.business.persistence.PriceType;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.business.persistence.PaymentType;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
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
        if (languageType.getPriceTypeList() == null) {
            languageType.setPriceTypeList(new ArrayList<PriceType>());
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
            List<PriceType> attachedPriceTypeList = new ArrayList<PriceType>();
            for (PriceType priceTypeListPriceTypeToAttach : languageType.getPriceTypeList()) {
                priceTypeListPriceTypeToAttach = em.getReference(priceTypeListPriceTypeToAttach.getClass(), priceTypeListPriceTypeToAttach.getId());
                attachedPriceTypeList.add(priceTypeListPriceTypeToAttach);
            }
            languageType.setPriceTypeList(attachedPriceTypeList);
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
            for (PriceType priceTypeListPriceType : languageType.getPriceTypeList()) {
                LanguageType oldLanguageTypeOfPriceTypeListPriceType = priceTypeListPriceType.getLanguageType();
                priceTypeListPriceType.setLanguageType(languageType);
                priceTypeListPriceType = em.merge(priceTypeListPriceType);
                if (oldLanguageTypeOfPriceTypeListPriceType != null) {
                    oldLanguageTypeOfPriceTypeListPriceType.getPriceTypeList().remove(priceTypeListPriceType);
                    oldLanguageTypeOfPriceTypeListPriceType = em.merge(oldLanguageTypeOfPriceTypeListPriceType);
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
            List<PriceType> priceTypeListOld = persistentLanguageType.getPriceTypeList();
            List<PriceType> priceTypeListNew = languageType.getPriceTypeList();
            List<SexType> sexTypeListOld = persistentLanguageType.getSexTypeList();
            List<SexType> sexTypeListNew = languageType.getSexTypeList();
            List<PaymentType> paymentTypeListOld = persistentLanguageType.getPaymentTypeList();
            List<PaymentType> paymentTypeListNew = languageType.getPaymentTypeList();
            List<EmployeeType> employeeTypeListOld = persistentLanguageType.getEmployeeTypeList();
            List<EmployeeType> employeeTypeListNew = languageType.getEmployeeTypeList();
            List<ProductType> productTypeListOld = persistentLanguageType.getProductTypeList();
            List<ProductType> productTypeListNew = languageType.getProductTypeList();
            List<String> illegalOrphanMessages = null;
            for (PriceType priceTypeListOldPriceType : priceTypeListOld) {
                if (!priceTypeListNew.contains(priceTypeListOldPriceType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PriceType " + priceTypeListOldPriceType + " since its languageType field is not nullable.");
                }
            }
            for (SexType sexTypeListOldSexType : sexTypeListOld) {
                if (!sexTypeListNew.contains(sexTypeListOldSexType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SexType " + sexTypeListOldSexType + " since its languageType field is not nullable.");
                }
            }
            for (PaymentType paymentTypeListOldPaymentType : paymentTypeListOld) {
                if (!paymentTypeListNew.contains(paymentTypeListOldPaymentType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaymentType " + paymentTypeListOldPaymentType + " since its languageType field is not nullable.");
                }
            }
            for (EmployeeType employeeTypeListOldEmployeeType : employeeTypeListOld) {
                if (!employeeTypeListNew.contains(employeeTypeListOldEmployeeType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeType " + employeeTypeListOldEmployeeType + " since its languageType field is not nullable.");
                }
            }
            for (ProductType productTypeListOldProductType : productTypeListOld) {
                if (!productTypeListNew.contains(productTypeListOldProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductType " + productTypeListOldProductType + " since its languageType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PriceType> attachedPriceTypeListNew = new ArrayList<PriceType>();
            for (PriceType priceTypeListNewPriceTypeToAttach : priceTypeListNew) {
                priceTypeListNewPriceTypeToAttach = em.getReference(priceTypeListNewPriceTypeToAttach.getClass(), priceTypeListNewPriceTypeToAttach.getId());
                attachedPriceTypeListNew.add(priceTypeListNewPriceTypeToAttach);
            }
            priceTypeListNew = attachedPriceTypeListNew;
            languageType.setPriceTypeList(priceTypeListNew);
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
            List<PriceType> priceTypeListOrphanCheck = languageType.getPriceTypeList();
            for (PriceType priceTypeListOrphanCheckPriceType : priceTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the PriceType " + priceTypeListOrphanCheckPriceType + " in its priceTypeList field has a non-nullable languageType field.");
            }
            List<SexType> sexTypeListOrphanCheck = languageType.getSexTypeList();
            for (SexType sexTypeListOrphanCheckSexType : sexTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the SexType " + sexTypeListOrphanCheckSexType + " in its sexTypeList field has a non-nullable languageType field.");
            }
            List<PaymentType> paymentTypeListOrphanCheck = languageType.getPaymentTypeList();
            for (PaymentType paymentTypeListOrphanCheckPaymentType : paymentTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the PaymentType " + paymentTypeListOrphanCheckPaymentType + " in its paymentTypeList field has a non-nullable languageType field.");
            }
            List<EmployeeType> employeeTypeListOrphanCheck = languageType.getEmployeeTypeList();
            for (EmployeeType employeeTypeListOrphanCheckEmployeeType : employeeTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the EmployeeType " + employeeTypeListOrphanCheckEmployeeType + " in its employeeTypeList field has a non-nullable languageType field.");
            }
            List<ProductType> productTypeListOrphanCheck = languageType.getProductTypeList();
            for (ProductType productTypeListOrphanCheckProductType : productTypeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LanguageType (" + languageType + ") cannot be destroyed since the ProductType " + productTypeListOrphanCheckProductType + " in its productTypeList field has a non-nullable languageType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
