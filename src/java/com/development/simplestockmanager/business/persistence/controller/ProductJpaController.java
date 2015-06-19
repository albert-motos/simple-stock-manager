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
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.business.persistence.ProductType;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getStockList() == null) {
            product.setStockList(new ArrayList<Stock>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Brand brand = product.getBrand();
            if (brand != null) {
                brand = em.getReference(brand.getClass(), brand.getId());
                product.setBrand(brand);
            }
            ProductType productType = product.getProductType();
            if (productType != null) {
                productType = em.getReference(productType.getClass(), productType.getId());
                product.setProductType(productType);
            }
            Provider provider = product.getProvider();
            if (provider != null) {
                provider = em.getReference(provider.getClass(), provider.getId());
                product.setProvider(provider);
            }
            List<Stock> attachedStockList = new ArrayList<Stock>();
            for (Stock stockListStockToAttach : product.getStockList()) {
                stockListStockToAttach = em.getReference(stockListStockToAttach.getClass(), stockListStockToAttach.getId());
                attachedStockList.add(stockListStockToAttach);
            }
            product.setStockList(attachedStockList);
            em.persist(product);
            if (brand != null) {
                brand.getProductList().add(product);
                brand = em.merge(brand);
            }
            if (productType != null) {
                productType.getProductList().add(product);
                productType = em.merge(productType);
            }
            if (provider != null) {
                provider.getProductList().add(product);
                provider = em.merge(provider);
            }
            for (Stock stockListStock : product.getStockList()) {
                Product oldProductOfStockListStock = stockListStock.getProduct();
                stockListStock.setProduct(product);
                stockListStock = em.merge(stockListStock);
                if (oldProductOfStockListStock != null) {
                    oldProductOfStockListStock.getStockList().remove(stockListStock);
                    oldProductOfStockListStock = em.merge(oldProductOfStockListStock);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Brand brandOld = persistentProduct.getBrand();
            Brand brandNew = product.getBrand();
            ProductType productTypeOld = persistentProduct.getProductType();
            ProductType productTypeNew = product.getProductType();
            Provider providerOld = persistentProduct.getProvider();
            Provider providerNew = product.getProvider();
            List<Stock> stockListOld = persistentProduct.getStockList();
            List<Stock> stockListNew = product.getStockList();
            List<String> illegalOrphanMessages = null;
            for (Stock stockListOldStock : stockListOld) {
                if (!stockListNew.contains(stockListOldStock)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Stock " + stockListOldStock + " since its product field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (brandNew != null) {
                brandNew = em.getReference(brandNew.getClass(), brandNew.getId());
                product.setBrand(brandNew);
            }
            if (productTypeNew != null) {
                productTypeNew = em.getReference(productTypeNew.getClass(), productTypeNew.getId());
                product.setProductType(productTypeNew);
            }
            if (providerNew != null) {
                providerNew = em.getReference(providerNew.getClass(), providerNew.getId());
                product.setProvider(providerNew);
            }
            List<Stock> attachedStockListNew = new ArrayList<Stock>();
            for (Stock stockListNewStockToAttach : stockListNew) {
                stockListNewStockToAttach = em.getReference(stockListNewStockToAttach.getClass(), stockListNewStockToAttach.getId());
                attachedStockListNew.add(stockListNewStockToAttach);
            }
            stockListNew = attachedStockListNew;
            product.setStockList(stockListNew);
            product = em.merge(product);
            if (brandOld != null && !brandOld.equals(brandNew)) {
                brandOld.getProductList().remove(product);
                brandOld = em.merge(brandOld);
            }
            if (brandNew != null && !brandNew.equals(brandOld)) {
                brandNew.getProductList().add(product);
                brandNew = em.merge(brandNew);
            }
            if (productTypeOld != null && !productTypeOld.equals(productTypeNew)) {
                productTypeOld.getProductList().remove(product);
                productTypeOld = em.merge(productTypeOld);
            }
            if (productTypeNew != null && !productTypeNew.equals(productTypeOld)) {
                productTypeNew.getProductList().add(product);
                productTypeNew = em.merge(productTypeNew);
            }
            if (providerOld != null && !providerOld.equals(providerNew)) {
                providerOld.getProductList().remove(product);
                providerOld = em.merge(providerOld);
            }
            if (providerNew != null && !providerNew.equals(providerOld)) {
                providerNew.getProductList().add(product);
                providerNew = em.merge(providerNew);
            }
            for (Stock stockListNewStock : stockListNew) {
                if (!stockListOld.contains(stockListNewStock)) {
                    Product oldProductOfStockListNewStock = stockListNewStock.getProduct();
                    stockListNewStock.setProduct(product);
                    stockListNewStock = em.merge(stockListNewStock);
                    if (oldProductOfStockListNewStock != null && !oldProductOfStockListNewStock.equals(product)) {
                        oldProductOfStockListNewStock.getStockList().remove(stockListNewStock);
                        oldProductOfStockListNewStock = em.merge(oldProductOfStockListNewStock);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Stock> stockListOrphanCheck = product.getStockList();
            for (Stock stockListOrphanCheckStock : stockListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Stock " + stockListOrphanCheckStock + " in its stockList field has a non-nullable product field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Brand brand = product.getBrand();
            if (brand != null) {
                brand.getProductList().remove(product);
                brand = em.merge(brand);
            }
            ProductType productType = product.getProductType();
            if (productType != null) {
                productType.getProductList().remove(product);
                productType = em.merge(productType);
            }
            Provider provider = product.getProvider();
            if (provider != null) {
                provider.getProductList().remove(product);
                provider = em.merge(provider);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
