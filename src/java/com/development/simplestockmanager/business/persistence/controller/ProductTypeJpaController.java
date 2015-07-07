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
import com.development.simplestockmanager.business.persistence.LanguageType;
import com.development.simplestockmanager.business.persistence.ProductType;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class ProductTypeJpaController implements Serializable {

    public ProductTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductType productType) {
        if (productType.getProductTypeList() == null) {
            productType.setProductTypeList(new ArrayList<ProductType>());
        }
        if (productType.getProductList() == null) {
            productType.setProductList(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = productType.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                productType.setCreatedUser(createdUser);
            }
            LanguageType languageType = productType.getLanguageType();
            if (languageType != null) {
                languageType = em.getReference(languageType.getClass(), languageType.getId());
                productType.setLanguageType(languageType);
            }
            ProductType referencedType = productType.getReferencedType();
            if (referencedType != null) {
                referencedType = em.getReference(referencedType.getClass(), referencedType.getId());
                productType.setReferencedType(referencedType);
            }
            Employee lastModifiedUser = productType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                productType.setLastModifiedUser(lastModifiedUser);
            }
            List<ProductType> attachedProductTypeList = new ArrayList<ProductType>();
            for (ProductType productTypeListProductTypeToAttach : productType.getProductTypeList()) {
                productTypeListProductTypeToAttach = em.getReference(productTypeListProductTypeToAttach.getClass(), productTypeListProductTypeToAttach.getId());
                attachedProductTypeList.add(productTypeListProductTypeToAttach);
            }
            productType.setProductTypeList(attachedProductTypeList);
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : productType.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getId());
                attachedProductList.add(productListProductToAttach);
            }
            productType.setProductList(attachedProductList);
            em.persist(productType);
            if (createdUser != null) {
                createdUser.getProductTypeList().add(productType);
                createdUser = em.merge(createdUser);
            }
            if (languageType != null) {
                languageType.getProductTypeList().add(productType);
                languageType = em.merge(languageType);
            }
            if (referencedType != null) {
                referencedType.getProductTypeList().add(productType);
                referencedType = em.merge(referencedType);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getProductTypeList().add(productType);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (ProductType productTypeListProductType : productType.getProductTypeList()) {
                ProductType oldReferencedTypeOfProductTypeListProductType = productTypeListProductType.getReferencedType();
                productTypeListProductType.setReferencedType(productType);
                productTypeListProductType = em.merge(productTypeListProductType);
                if (oldReferencedTypeOfProductTypeListProductType != null) {
                    oldReferencedTypeOfProductTypeListProductType.getProductTypeList().remove(productTypeListProductType);
                    oldReferencedTypeOfProductTypeListProductType = em.merge(oldReferencedTypeOfProductTypeListProductType);
                }
            }
            for (Product productListProduct : productType.getProductList()) {
                ProductType oldProductTypeOfProductListProduct = productListProduct.getProductType();
                productListProduct.setProductType(productType);
                productListProduct = em.merge(productListProduct);
                if (oldProductTypeOfProductListProduct != null) {
                    oldProductTypeOfProductListProduct.getProductList().remove(productListProduct);
                    oldProductTypeOfProductListProduct = em.merge(oldProductTypeOfProductListProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductType productType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductType persistentProductType = em.find(ProductType.class, productType.getId());
            Employee createdUserOld = persistentProductType.getCreatedUser();
            Employee createdUserNew = productType.getCreatedUser();
            LanguageType languageTypeOld = persistentProductType.getLanguageType();
            LanguageType languageTypeNew = productType.getLanguageType();
            ProductType referencedTypeOld = persistentProductType.getReferencedType();
            ProductType referencedTypeNew = productType.getReferencedType();
            Employee lastModifiedUserOld = persistentProductType.getLastModifiedUser();
            Employee lastModifiedUserNew = productType.getLastModifiedUser();
            List<ProductType> productTypeListOld = persistentProductType.getProductTypeList();
            List<ProductType> productTypeListNew = productType.getProductTypeList();
            List<Product> productListOld = persistentProductType.getProductList();
            List<Product> productListNew = productType.getProductList();
            List<String> illegalOrphanMessages = null;
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productListOldProduct + " since its productType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                productType.setCreatedUser(createdUserNew);
            }
            if (languageTypeNew != null) {
                languageTypeNew = em.getReference(languageTypeNew.getClass(), languageTypeNew.getId());
                productType.setLanguageType(languageTypeNew);
            }
            if (referencedTypeNew != null) {
                referencedTypeNew = em.getReference(referencedTypeNew.getClass(), referencedTypeNew.getId());
                productType.setReferencedType(referencedTypeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                productType.setLastModifiedUser(lastModifiedUserNew);
            }
            List<ProductType> attachedProductTypeListNew = new ArrayList<ProductType>();
            for (ProductType productTypeListNewProductTypeToAttach : productTypeListNew) {
                productTypeListNewProductTypeToAttach = em.getReference(productTypeListNewProductTypeToAttach.getClass(), productTypeListNewProductTypeToAttach.getId());
                attachedProductTypeListNew.add(productTypeListNewProductTypeToAttach);
            }
            productTypeListNew = attachedProductTypeListNew;
            productType.setProductTypeList(productTypeListNew);
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            productType.setProductList(productListNew);
            productType = em.merge(productType);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getProductTypeList().remove(productType);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getProductTypeList().add(productType);
                createdUserNew = em.merge(createdUserNew);
            }
            if (languageTypeOld != null && !languageTypeOld.equals(languageTypeNew)) {
                languageTypeOld.getProductTypeList().remove(productType);
                languageTypeOld = em.merge(languageTypeOld);
            }
            if (languageTypeNew != null && !languageTypeNew.equals(languageTypeOld)) {
                languageTypeNew.getProductTypeList().add(productType);
                languageTypeNew = em.merge(languageTypeNew);
            }
            if (referencedTypeOld != null && !referencedTypeOld.equals(referencedTypeNew)) {
                referencedTypeOld.getProductTypeList().remove(productType);
                referencedTypeOld = em.merge(referencedTypeOld);
            }
            if (referencedTypeNew != null && !referencedTypeNew.equals(referencedTypeOld)) {
                referencedTypeNew.getProductTypeList().add(productType);
                referencedTypeNew = em.merge(referencedTypeNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getProductTypeList().remove(productType);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getProductTypeList().add(productType);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (ProductType productTypeListOldProductType : productTypeListOld) {
                if (!productTypeListNew.contains(productTypeListOldProductType)) {
                    productTypeListOldProductType.setReferencedType(null);
                    productTypeListOldProductType = em.merge(productTypeListOldProductType);
                }
            }
            for (ProductType productTypeListNewProductType : productTypeListNew) {
                if (!productTypeListOld.contains(productTypeListNewProductType)) {
                    ProductType oldReferencedTypeOfProductTypeListNewProductType = productTypeListNewProductType.getReferencedType();
                    productTypeListNewProductType.setReferencedType(productType);
                    productTypeListNewProductType = em.merge(productTypeListNewProductType);
                    if (oldReferencedTypeOfProductTypeListNewProductType != null && !oldReferencedTypeOfProductTypeListNewProductType.equals(productType)) {
                        oldReferencedTypeOfProductTypeListNewProductType.getProductTypeList().remove(productTypeListNewProductType);
                        oldReferencedTypeOfProductTypeListNewProductType = em.merge(oldReferencedTypeOfProductTypeListNewProductType);
                    }
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    ProductType oldProductTypeOfProductListNewProduct = productListNewProduct.getProductType();
                    productListNewProduct.setProductType(productType);
                    productListNewProduct = em.merge(productListNewProduct);
                    if (oldProductTypeOfProductListNewProduct != null && !oldProductTypeOfProductListNewProduct.equals(productType)) {
                        oldProductTypeOfProductListNewProduct.getProductList().remove(productListNewProduct);
                        oldProductTypeOfProductListNewProduct = em.merge(oldProductTypeOfProductListNewProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = productType.getId();
                if (findProductType(id) == null) {
                    throw new NonexistentEntityException("The productType with id " + id + " no longer exists.");
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
            ProductType productType;
            try {
                productType = em.getReference(ProductType.class, id);
                productType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Product> productListOrphanCheck = productType.getProductList();
            for (Product productListOrphanCheckProduct : productListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductType (" + productType + ") cannot be destroyed since the Product " + productListOrphanCheckProduct + " in its productList field has a non-nullable productType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = productType.getCreatedUser();
            if (createdUser != null) {
                createdUser.getProductTypeList().remove(productType);
                createdUser = em.merge(createdUser);
            }
            LanguageType languageType = productType.getLanguageType();
            if (languageType != null) {
                languageType.getProductTypeList().remove(productType);
                languageType = em.merge(languageType);
            }
            ProductType referencedType = productType.getReferencedType();
            if (referencedType != null) {
                referencedType.getProductTypeList().remove(productType);
                referencedType = em.merge(referencedType);
            }
            Employee lastModifiedUser = productType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getProductTypeList().remove(productType);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            List<ProductType> productTypeList = productType.getProductTypeList();
            for (ProductType productTypeListProductType : productTypeList) {
                productTypeListProductType.setReferencedType(null);
                productTypeListProductType = em.merge(productTypeListProductType);
            }
            em.remove(productType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductType> findProductTypeEntities() {
        return findProductTypeEntities(true, -1, -1);
    }

    public List<ProductType> findProductTypeEntities(int maxResults, int firstResult) {
        return findProductTypeEntities(false, maxResults, firstResult);
    }

    private List<ProductType> findProductTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductType.class));
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

    public ProductType findProductType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductType.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductType> rt = cq.from(ProductType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
