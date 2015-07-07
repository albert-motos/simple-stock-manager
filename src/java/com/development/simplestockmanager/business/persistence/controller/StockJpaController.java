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
import com.development.simplestockmanager.business.persistence.Store;
import com.development.simplestockmanager.business.persistence.Price;
import java.util.ArrayList;
import java.util.List;
import com.development.simplestockmanager.business.persistence.Record;
import com.development.simplestockmanager.business.persistence.Item;
import com.development.simplestockmanager.business.persistence.Stock;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author foxtrot
 */
public class StockJpaController implements Serializable {

    public StockJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stock stock) {
        if (stock.getPriceList() == null) {
            stock.setPriceList(new ArrayList<Price>());
        }
        if (stock.getRecordList() == null) {
            stock.setRecordList(new ArrayList<Record>());
        }
        if (stock.getItemList() == null) {
            stock.setItemList(new ArrayList<Item>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee createdUser = stock.getCreatedUser();
            if (createdUser != null) {
                createdUser = em.getReference(createdUser.getClass(), createdUser.getId());
                stock.setCreatedUser(createdUser);
            }
            Product product = stock.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getId());
                stock.setProduct(product);
            }
            Store store = stock.getStore();
            if (store != null) {
                store = em.getReference(store.getClass(), store.getId());
                stock.setStore(store);
            }
            Employee lastModifiedUser = stock.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser = em.getReference(lastModifiedUser.getClass(), lastModifiedUser.getId());
                stock.setLastModifiedUser(lastModifiedUser);
            }
            List<Price> attachedPriceList = new ArrayList<Price>();
            for (Price priceListPriceToAttach : stock.getPriceList()) {
                priceListPriceToAttach = em.getReference(priceListPriceToAttach.getClass(), priceListPriceToAttach.getId());
                attachedPriceList.add(priceListPriceToAttach);
            }
            stock.setPriceList(attachedPriceList);
            List<Record> attachedRecordList = new ArrayList<Record>();
            for (Record recordListRecordToAttach : stock.getRecordList()) {
                recordListRecordToAttach = em.getReference(recordListRecordToAttach.getClass(), recordListRecordToAttach.getId());
                attachedRecordList.add(recordListRecordToAttach);
            }
            stock.setRecordList(attachedRecordList);
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : stock.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getId());
                attachedItemList.add(itemListItemToAttach);
            }
            stock.setItemList(attachedItemList);
            em.persist(stock);
            if (createdUser != null) {
                createdUser.getStockList().add(stock);
                createdUser = em.merge(createdUser);
            }
            if (product != null) {
                product.getStockList().add(stock);
                product = em.merge(product);
            }
            if (store != null) {
                store.getStockList().add(stock);
                store = em.merge(store);
            }
            if (lastModifiedUser != null) {
                lastModifiedUser.getStockList().add(stock);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            for (Price priceListPrice : stock.getPriceList()) {
                Stock oldStockOfPriceListPrice = priceListPrice.getStock();
                priceListPrice.setStock(stock);
                priceListPrice = em.merge(priceListPrice);
                if (oldStockOfPriceListPrice != null) {
                    oldStockOfPriceListPrice.getPriceList().remove(priceListPrice);
                    oldStockOfPriceListPrice = em.merge(oldStockOfPriceListPrice);
                }
            }
            for (Record recordListRecord : stock.getRecordList()) {
                Stock oldStockOfRecordListRecord = recordListRecord.getStock();
                recordListRecord.setStock(stock);
                recordListRecord = em.merge(recordListRecord);
                if (oldStockOfRecordListRecord != null) {
                    oldStockOfRecordListRecord.getRecordList().remove(recordListRecord);
                    oldStockOfRecordListRecord = em.merge(oldStockOfRecordListRecord);
                }
            }
            for (Item itemListItem : stock.getItemList()) {
                Stock oldStockOfItemListItem = itemListItem.getStock();
                itemListItem.setStock(stock);
                itemListItem = em.merge(itemListItem);
                if (oldStockOfItemListItem != null) {
                    oldStockOfItemListItem.getItemList().remove(itemListItem);
                    oldStockOfItemListItem = em.merge(oldStockOfItemListItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stock stock) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stock persistentStock = em.find(Stock.class, stock.getId());
            Employee createdUserOld = persistentStock.getCreatedUser();
            Employee createdUserNew = stock.getCreatedUser();
            Product productOld = persistentStock.getProduct();
            Product productNew = stock.getProduct();
            Store storeOld = persistentStock.getStore();
            Store storeNew = stock.getStore();
            Employee lastModifiedUserOld = persistentStock.getLastModifiedUser();
            Employee lastModifiedUserNew = stock.getLastModifiedUser();
            List<Price> priceListOld = persistentStock.getPriceList();
            List<Price> priceListNew = stock.getPriceList();
            List<Record> recordListOld = persistentStock.getRecordList();
            List<Record> recordListNew = stock.getRecordList();
            List<Item> itemListOld = persistentStock.getItemList();
            List<Item> itemListNew = stock.getItemList();
            List<String> illegalOrphanMessages = null;
            for (Price priceListOldPrice : priceListOld) {
                if (!priceListNew.contains(priceListOldPrice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Price " + priceListOldPrice + " since its stock field is not nullable.");
                }
            }
            for (Record recordListOldRecord : recordListOld) {
                if (!recordListNew.contains(recordListOldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordListOldRecord + " since its stock field is not nullable.");
                }
            }
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its stock field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdUserNew != null) {
                createdUserNew = em.getReference(createdUserNew.getClass(), createdUserNew.getId());
                stock.setCreatedUser(createdUserNew);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getId());
                stock.setProduct(productNew);
            }
            if (storeNew != null) {
                storeNew = em.getReference(storeNew.getClass(), storeNew.getId());
                stock.setStore(storeNew);
            }
            if (lastModifiedUserNew != null) {
                lastModifiedUserNew = em.getReference(lastModifiedUserNew.getClass(), lastModifiedUserNew.getId());
                stock.setLastModifiedUser(lastModifiedUserNew);
            }
            List<Price> attachedPriceListNew = new ArrayList<Price>();
            for (Price priceListNewPriceToAttach : priceListNew) {
                priceListNewPriceToAttach = em.getReference(priceListNewPriceToAttach.getClass(), priceListNewPriceToAttach.getId());
                attachedPriceListNew.add(priceListNewPriceToAttach);
            }
            priceListNew = attachedPriceListNew;
            stock.setPriceList(priceListNew);
            List<Record> attachedRecordListNew = new ArrayList<Record>();
            for (Record recordListNewRecordToAttach : recordListNew) {
                recordListNewRecordToAttach = em.getReference(recordListNewRecordToAttach.getClass(), recordListNewRecordToAttach.getId());
                attachedRecordListNew.add(recordListNewRecordToAttach);
            }
            recordListNew = attachedRecordListNew;
            stock.setRecordList(recordListNew);
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getId());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            stock.setItemList(itemListNew);
            stock = em.merge(stock);
            if (createdUserOld != null && !createdUserOld.equals(createdUserNew)) {
                createdUserOld.getStockList().remove(stock);
                createdUserOld = em.merge(createdUserOld);
            }
            if (createdUserNew != null && !createdUserNew.equals(createdUserOld)) {
                createdUserNew.getStockList().add(stock);
                createdUserNew = em.merge(createdUserNew);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getStockList().remove(stock);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getStockList().add(stock);
                productNew = em.merge(productNew);
            }
            if (storeOld != null && !storeOld.equals(storeNew)) {
                storeOld.getStockList().remove(stock);
                storeOld = em.merge(storeOld);
            }
            if (storeNew != null && !storeNew.equals(storeOld)) {
                storeNew.getStockList().add(stock);
                storeNew = em.merge(storeNew);
            }
            if (lastModifiedUserOld != null && !lastModifiedUserOld.equals(lastModifiedUserNew)) {
                lastModifiedUserOld.getStockList().remove(stock);
                lastModifiedUserOld = em.merge(lastModifiedUserOld);
            }
            if (lastModifiedUserNew != null && !lastModifiedUserNew.equals(lastModifiedUserOld)) {
                lastModifiedUserNew.getStockList().add(stock);
                lastModifiedUserNew = em.merge(lastModifiedUserNew);
            }
            for (Price priceListNewPrice : priceListNew) {
                if (!priceListOld.contains(priceListNewPrice)) {
                    Stock oldStockOfPriceListNewPrice = priceListNewPrice.getStock();
                    priceListNewPrice.setStock(stock);
                    priceListNewPrice = em.merge(priceListNewPrice);
                    if (oldStockOfPriceListNewPrice != null && !oldStockOfPriceListNewPrice.equals(stock)) {
                        oldStockOfPriceListNewPrice.getPriceList().remove(priceListNewPrice);
                        oldStockOfPriceListNewPrice = em.merge(oldStockOfPriceListNewPrice);
                    }
                }
            }
            for (Record recordListNewRecord : recordListNew) {
                if (!recordListOld.contains(recordListNewRecord)) {
                    Stock oldStockOfRecordListNewRecord = recordListNewRecord.getStock();
                    recordListNewRecord.setStock(stock);
                    recordListNewRecord = em.merge(recordListNewRecord);
                    if (oldStockOfRecordListNewRecord != null && !oldStockOfRecordListNewRecord.equals(stock)) {
                        oldStockOfRecordListNewRecord.getRecordList().remove(recordListNewRecord);
                        oldStockOfRecordListNewRecord = em.merge(oldStockOfRecordListNewRecord);
                    }
                }
            }
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    Stock oldStockOfItemListNewItem = itemListNewItem.getStock();
                    itemListNewItem.setStock(stock);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldStockOfItemListNewItem != null && !oldStockOfItemListNewItem.equals(stock)) {
                        oldStockOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldStockOfItemListNewItem = em.merge(oldStockOfItemListNewItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = stock.getId();
                if (findStock(id) == null) {
                    throw new NonexistentEntityException("The stock with id " + id + " no longer exists.");
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
            Stock stock;
            try {
                stock = em.getReference(Stock.class, id);
                stock.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stock with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Price> priceListOrphanCheck = stock.getPriceList();
            for (Price priceListOrphanCheckPrice : priceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Stock (" + stock + ") cannot be destroyed since the Price " + priceListOrphanCheckPrice + " in its priceList field has a non-nullable stock field.");
            }
            List<Record> recordListOrphanCheck = stock.getRecordList();
            for (Record recordListOrphanCheckRecord : recordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Stock (" + stock + ") cannot be destroyed since the Record " + recordListOrphanCheckRecord + " in its recordList field has a non-nullable stock field.");
            }
            List<Item> itemListOrphanCheck = stock.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Stock (" + stock + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable stock field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee createdUser = stock.getCreatedUser();
            if (createdUser != null) {
                createdUser.getStockList().remove(stock);
                createdUser = em.merge(createdUser);
            }
            Product product = stock.getProduct();
            if (product != null) {
                product.getStockList().remove(stock);
                product = em.merge(product);
            }
            Store store = stock.getStore();
            if (store != null) {
                store.getStockList().remove(stock);
                store = em.merge(store);
            }
            Employee lastModifiedUser = stock.getLastModifiedUser();
            if (lastModifiedUser != null) {
                lastModifiedUser.getStockList().remove(stock);
                lastModifiedUser = em.merge(lastModifiedUser);
            }
            em.remove(stock);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Stock> findStockEntities() {
        return findStockEntities(true, -1, -1);
    }

    public List<Stock> findStockEntities(int maxResults, int firstResult) {
        return findStockEntities(false, maxResults, firstResult);
    }

    private List<Stock> findStockEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Stock.class));
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

    public Stock findStock(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stock.class, id);
        } finally {
            em.close();
        }
    }

    public int getStockCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Stock> rt = cq.from(Stock.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
