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
import com.development.simplestockmanager.business.persistence.ProductTypeTranslation;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.ProductType;
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
        if (productType.getProductTypeTranslationList() == null) {
            productType.setProductTypeTranslationList(new ArrayList<ProductTypeTranslation>());
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
            Employee lastModifiedUser = productType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                productType.setLastModifiedUser(lastModifiedUser);
            }
            List<ProductTypeTranslation> attachedProductTypeTranslationList = new ArrayList<ProductTypeTranslation>();
            for (ProductTypeTranslation productTypeTranslationListProductTypeTranslationToAttach : productType.getProductTypeTranslationList()) {
                productTypeTranslationListProductTypeTranslationToAttach = em.getReference(productTypeTranslationListProductTypeTranslationToAttach.getClass(), productTypeTranslationListProductTypeTranslationToAttach.getId());
                attachedProductTypeTranslationList.add(productTypeTranslationListProductTypeTranslationToAttach);
            }
            productType.setProductTypeTranslationList(attachedProductTypeTranslationList);
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
            if (lastModifiedUser != null) {
                lastModifiedUser.getProductTypeList().add(productType);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (ProductTypeTranslation productTypeTranslationListProductTypeTranslation : productType.getProductTypeTranslationList()) {
                ProductType oldReferenceOfProductTypeTranslationListProductTypeTranslation = productTypeTranslationListProductTypeTranslation.getReference();
                productTypeTranslationListProductTypeTranslation.setReference(productType);
                productTypeTranslationListProductTypeTranslation = em.merge(productTypeTranslationListProductTypeTranslation);
                if (oldReferenceOfProductTypeTranslationListProductTypeTranslation != null) {
                    oldReferenceOfProductTypeTranslationListProductTypeTranslation.getProductTypeTranslationList().remove(productTypeTranslationListProductTypeTranslation);
                    oldReferenceOfProductTypeTranslationListProductTypeTranslation = em.merge(oldReferenceOfProductTypeTranslationListProductTypeTranslation);
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
            Employee lastModifiedUserOld = persistentProductType.getLastModifiedUser();
            Employee lastModifiedUserNew = productType.getLastModifiedUser();
            List<ProductTypeTranslation> productTypeTranslationListOld = persistentProductType.getProductTypeTranslationList();
            List<ProductTypeTranslation> productTypeTranslationListNew = productType.getProductTypeTranslationList();
            List<Product> productListOld = persistentProductType.getProductList();
            List<Product> productListNew = productType.getProductList();
            List<String> illegalOrphanMessages = null;
            for (ProductTypeTranslation productTypeTranslationListOldProductTypeTranslation : productTypeTranslationListOld) {
                if (!productTypeTranslationListNew.contains(productTypeTranslationListOldProductTypeTranslation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductTypeTranslation " + productTypeTranslationListOldProductTypeTranslation + " since its reference field is not nullable.");
                }
            }
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productListOldProduct + " since its productType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                productType.setCreatedUser(createdUserNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                productType.setLastModifiedUser(lastModifiedUserNew);
            }
            List<ProductTypeTranslation> attachedProductTypeTranslationListNew = new ArrayList<ProductTypeTranslation>();
            for (ProductTypeTranslation productTypeTranslationListNewProductTypeTranslationToAttach : productTypeTranslationListNew) {
                productTypeTranslationListNewProductTypeTranslationToAttach = em.getReference(productTypeTranslationListNewProductTypeTranslationToAttach.getClass(), productTypeTranslationListNewProductTypeTranslationToAttach.getId());
                attachedProductTypeTranslationListNew.add(productTypeTranslationListNewProductTypeTranslationToAttach);
            }
            productTypeTranslationListNew = attachedProductTypeTranslationListNew;
            productType.setProductTypeTranslationList(productTypeTranslationListNew);
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
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getProductTypeList().remove(productType);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getProductTypeList().add(productType);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (ProductTypeTranslation productTypeTranslationListNewProductTypeTranslation : productTypeTranslationListNew) {
                if (!productTypeTranslationListOld.contains(productTypeTranslationListNewProductTypeTranslation)) {
                    ProductType oldReferenceOfProductTypeTranslationListNewProductTypeTranslation = productTypeTranslationListNewProductTypeTranslation.getReference();
                    productTypeTranslationListNewProductTypeTranslation.setReference(productType);
                    productTypeTranslationListNewProductTypeTranslation = em.merge(productTypeTranslationListNewProductTypeTranslation);
                    if (oldReferenceOfProductTypeTranslationListNewProductTypeTranslation != null && !oldReferenceOfProductTypeTranslationListNewProductTypeTranslation.equals(productType)) {
                        oldReferenceOfProductTypeTranslationListNewProductTypeTranslation.getProductTypeTranslationList().remove(productTypeTranslationListNewProductTypeTranslation);
                        oldReferenceOfProductTypeTranslationListNewProductTypeTranslation = em.merge(oldReferenceOfProductTypeTranslationListNewProductTypeTranslation);
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
            List<ProductTypeTranslation> productTypeTranslationListOrphanCheck = productType.getProductTypeTranslationList();
            for (ProductTypeTranslation productTypeTranslationListOrphanCheckProductTypeTranslation : productTypeTranslationListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductType (" + productType + ") cannot be destroyed since the ProductTypeTranslation " + productTypeTranslationListOrphanCheckProductTypeTranslation + " in its productTypeTranslationList field has a non-nullable reference field.");
            }
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
            Employee lastModifiedUser = productType.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getProductTypeList().remove(productType);
                lastModifiedUser = em.merge(lastModifiedUser);
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
