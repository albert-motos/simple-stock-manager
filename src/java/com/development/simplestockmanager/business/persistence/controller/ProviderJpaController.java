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
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.Provider;
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
public class ProviderJpaController implements Serializable {

    public ProviderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provider provider) {
        if (provider.getProductList() == null) {
            provider.setProductList(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = provider.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                provider.setCreatedUser(createdUser);
            }
            Employee lastModifiedUser = provider.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                provider.setLastModifiedUser(lastModifiedUser);
            }
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : provider.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getId());
                attachedProductList.add(productListProductToAttach);
            }
            provider.setProductList(attachedProductList);
            em.persist(provider);
            if (createdUser != null) {
                createdUser.getProviderList().add(provider);
                createdUser = em.merge(createdUser);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getProviderList().add(provider);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (Product productListProduct : provider.getProductList()) {
                Provider oldProviderOfProductListProduct = productListProduct.getProvider();
                productListProduct.setProvider(provider);
                productListProduct = em.merge(productListProduct);
                if (oldProviderOfProductListProduct != null) {
                    oldProviderOfProductListProduct.getProductList().remove(productListProduct);
                    oldProviderOfProductListProduct = em.merge(oldProviderOfProductListProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provider provider) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provider persistentProvider = em.find(Provider.class, provider.getId());
            Employee createdUserOld = persistentProvider.getCreatedUser();
            Employee createdUserNew = provider.getCreatedUser();
            Employee lastModifiedUserOld = persistentProvider.getLastModifiedUser();
            Employee lastModifiedUserNew = provider.getLastModifiedUser();
            List<Product> productListOld = persistentProvider.getProductList();
            List<Product> productListNew = provider.getProductList();
            List<String> illegalOrphanMessages = null;
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productListOldProduct + " since its provider field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                provider.setCreatedUser(createdUserNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                provider.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            provider.setProductList(productListNew);
            provider = em.merge(provider);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getProviderList().remove(provider);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getProviderList().add(provider);
                createdUserNew = em.merge(createdUserNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getProviderList().remove(provider);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getProviderList().add(provider);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    Provider oldProviderOfProductListNewProduct = productListNewProduct.getProvider();
                    productListNewProduct.setProvider(provider);
                    productListNewProduct = em.merge(productListNewProduct);
                    if (oldProviderOfProductListNewProduct != null && !oldProviderOfProductListNewProduct.equals(provider)) {
                        oldProviderOfProductListNewProduct.getProductList().remove(productListNewProduct);
                        oldProviderOfProductListNewProduct = em.merge(oldProviderOfProductListNewProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = provider.getId();
                if (findProvider(id) == null) {
                    throw new NonexistentEntityException("The provider with id " + id + " no longer exists.");
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
            Provider provider;
            try {
                provider = em.getReference(Provider.class, id);
                provider.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provider with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Product> productListOrphanCheck = provider.getProductList();
            for (Product productListOrphanCheckProduct : productListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provider (" + provider + ") cannot be destroyed since the Product " + productListOrphanCheckProduct + " in its productList field has a non-nullable provider field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = provider.getCreatedUser();
            if (createdUser != null) {
                createdUser.getProviderList().remove(provider);
                createdUser = em.merge(createdUser);
            }
            Employee lastModifiedUser = provider.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getProviderList().remove(provider);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            em.remove(provider);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provider> findProviderEntities() {
        return findProviderEntities(true, -1, -1);
    }

    public List<Provider> findProviderEntities(int maxResults, int firstResult) {
        return findProviderEntities(false, maxResults, firstResult);
    }

    private List<Provider> findProviderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provider.class));
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

    public Provider findProvider(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provider.class, id);
        } finally {
            em.close();
        }
    }

    public int getProviderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provider> rt = cq.from(Provider.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
